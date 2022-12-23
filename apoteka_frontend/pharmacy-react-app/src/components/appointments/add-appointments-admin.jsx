import { useHistory } from "react-router-dom";
import ADService from "../common/appointment-axios/dermatologists-appointment-service"
import DService from "../common/dermatologists-axios/dermatologists-service"
import WorkTimeService from "../common/dermatologists-axios/dermatologists-work-time-service"
import PharmacyService from "../common/pharmacies-axios/pharmacies-service"
import React, { useMemo, useState, useEffect } from "react";
import allergiesService from "../common/allergies-axios/allergies-service";
import Table from "./dermatologist-work-time-table";
import workTimeService from "../common/dermatologists-axios/dermatologists-work-time-service"

function WorkTimeDermatologistsTable({dataTable}){
  const [data, setData] = useState(dataTable);
  // Using useEffect to call the API once mounted and set the data

  const columns = useMemo(
      () => [
        {
          // first group 
          Header: "Dermatologist shift",
          // First group columns
          columns: [
            {
              Header: "Dermatologist Id",
              accessor: "dermatologistId"
            },
            {
              Header: "Shift start",
              accessor: "shiftStart"
            },
            {
              Header: "Shift End",
              accessor: "shiftEnd"
            },
          ]
        }
      ],
      []
    );
  
  
  return(
      <Table columns={columns} data={data} />
  );
}


export default function AddDermatologistAppointment({}) {
    // Use the useTable Hook to send the columns and data to build the table
    const history = useHistory();
    const [dermatolog, setDermatolog] = useState([]);
    const [pharmacies, setPharmacies] = useState([]);
    const [appontments, setAppontments] = useState([]);
    const [workTime, setWorkTime] = useState([]);
    const [loaded, setLoaded] = useState(false);
    const [data1, setData] = useState([]);
    //const [submitted, setSubmitted] = useState(false);

    const [dermatologistWorkTime, setDermatologistWorkTime] = useState([]);

    var submitted = false;
    const post =
        {
            'id' : null,
            'isDone' : false,
            'date' : null,
            'appointmentStart' : null,
            'appointmentEnd' : null,
            'duration' : null,
            'dermatologistId' : null,
            'patientId' : -1,
            'pharmacyId' : localStorage.getItem('LogedUser').split(',')[0].split(':')[1],
            'price' : 0,
            'submitted' : false
        }

    useEffect(() => {
      workTimeService.getAllPharmacy(window.location.pathname.split('/')[2],localStorage.getItem('LogedUser').split(',')[0].split(':')[1])
      .then(response => {
              setData(data1.concat(response.data));
              setLoaded(true);
          })
      .catch(e => {
          console.log(e);
      });
  }, [loaded]);    
    


    // Using useEffect to call the API once mounted and set the data
        useEffect(() => {
            DService.get(window.location.pathname.split('/')[2])
            .then(response => {
                setDermatolog(response.data);
                    console.log(response.data);
                    })
            .catch(e => {
                console.log(e);
            });
        }, []);

        useEffect(() => {
            WorkTimeService.getAll()
        .then(response => {
                setWorkTime(response.data);
                console.log(response.data);
                })
        .catch(e => {
            console.log(e);
        });
        }, []);

        useEffect(() => {
            ADService.getAll()
        .then(response => {
            setAppontments(response.data);
                console.log(response.data);
                })
        .catch(e => {
            console.log(e);
        });
        }, []);

        useEffect(() => {
            PharmacyService.getAll()
            .then(response => {
                setPharmacies(response.data);
                    console.log(response.data);
                    })
            .catch(e => {
                console.log(e);
            });

        
        }, []);

        

        useEffect(()=>{


        let pids = String(dermatolog.pharmacy).split(" ").join("");

        let pids_array = pids.split(",");

        let pom = pids_array.length;
        
        
        for(let j = 0; j < pharmacies.length; j++) 
            {

                if(pids_array.includes(String(pharmacies[j].id)))
                {
                pom--;
                let opt = document.createElement('option');
                opt.innerHTML = pharmacies[j].name;
                opt.value = pharmacies[j].id;
                console.log(pharmacies[j].id);
               
                if(pom == 0)
                    
                    break;
                
            }

        }

        
        
    }, [dermatolog, pharmacies, workTime, appontments]);

    
    

    function HandleDate(e)
    {
        
        post.date =  document.getElementById("date").value;
    }

    function HandleStart(e)
    {
        post.appointmentStart =  document.getElementById("start").value;
    }

    function HandleEnd(e)
    {
        post.appointmentEnd =  document.getElementById("end").value;
    }

    function HandlePrice(e)
    {
        post.price =  document.getElementById("price").value;
    }

    function HandleClick(e)
    {
        post.date =  document.getElementById("date").value;
        post.appointmentStart =  document.getElementById("start").value;
        post.appointmentEnd =  document.getElementById("end").value;
        post.price = document.getElementById("price").value;
        post.dermatologistId = Number(window.location.pathname.split('/')[2]);
        let d1 = new Date(0,0,0,Number(String(post.appointmentEnd).split(":")[0]),Number(String(post.appointmentEnd).split(":")[1]));
        let d2 = new Date(0,0,0,Number(String(post.appointmentStart).split(":")[0]),Number(String(post.appointmentStart).split(":")[1]));
        let diff =(( (d1.getTime()) - (d2.getTime()))  / 1000);
        diff /= 60;
        post.duration = Number(Math.abs(Math.round(diff)));

        ADService.create(post)
        .catch(e => {
          console.log(e);
      });

      alert("Uspesno dodato");
        
    }

    return (
        <body>
          {loaded ? <WorkTimeDermatologistsTable dataTable={data1} /> : ''}
        <div className="submit-form">
            {post.submitted ? (
              <div>
                <h4>Uspesno dodato!</h4>
                <button className="btn btn-success" >
                  Dodaj
                </button>
              </div>
            ) : (
              <div>
                  <h3>Dodaj pregled kod dermatologa</h3>
                <br />
                <div className="form-group">
                  <label>Datum</label>
                  <input
                    onChange = {HandleDate}
                    type="date"
                    className="form-control"
                    id="date"
                    required
                    name="date"
                  />
                </div>
    
                <div className="form-group">
                  <label>Počinje</label>
                  <input
                    type="text"
                    onChange = {HandleStart}
                    className="form-control"
                    id="start"
                    required
                    name="start"
                  />
                </div>

                <div className="form-group">
                  <label>Završava se</label>
                  <input
                  onChange = {HandleEnd}
                    type="text"
                    className="form-control"
                    id="end"
                    required
                    name="end"
                  />
                </div>

                <div className="form-group">
                  <label>Cena</label>
                  <input
                  onChange = {HandlePrice}
                    type="number"
                    step="0.01"
                    className="form-control"
                    id="price"
                    required
                    name="price"
                  />
                </div>
                
    
                <button className="btn btn-success" onClick={HandleClick}>
                  Submit
                </button>
              </div>
            )}
            
          </div>
          </body>
    );
}