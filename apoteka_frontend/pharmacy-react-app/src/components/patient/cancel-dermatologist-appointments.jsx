import DerApoServise from "../common/appointment-axios/dermatologists-appointment-service"
import ReactDOM from 'react-dom';
import React, { useMemo, useState, useEffect } from "react";
import Table from "./table-cancel-appointment";
import { useHistory } from "react-router-dom";
import Derserv from "../common/dermatologists-axios/dermatologists-service"
export default function AppointmentsDermatologistsTable(){
  
    const [data, setData] = useState([]);
    const history = useHistory();
    const [dermatologists, setDermatologists] = useState([]);
    // Using useEffect to call the API once mounted and set the data
    useEffect(() => {
      let pomocna = JSON.parse(localStorage.getItem('LogedUser'));
      DerApoServise.getAppointmentsByUser(pomocna.id)
      .then(response => {
              setData(response.data);
              console.log(response.data);
              
            })
      .catch(e => {
          console.log(e);
      });

      Derserv.getAll()
      .then(response => {
              setDermatologists(response.data);
              console.log(response.data);
              
            })
      .catch(e => {
          console.log(e);
      });
  }, []);


  function sortPrice(isDecs)
  {
    data.sort(function(a,b){
      //return a.attributes.OBJECTID - b.attributes.OBJECTID;
      if(a.price == b.price)
          return 0;
      if(a.price < b.price)
          return -1;
      if(a.price > b.price)
          return 1;
  });
      if(isDecs==1)
      {
        data.reverse();
      }
  }

  function handlePeriodChange(sortInfo)
  {

    if(sortInfo==="cenaAsc")
    {
      sortPrice(0);
    }
    if(sortInfo==="cenaDecs")
    {
      sortPrice(1);
    }

    ReactDOM.unmountComponentAtNode(document.getElementById('tabel-div'));

    let element =  <div id="tabel-div"><Table id="table1" columns={columns} data={data} history={history}  dermatologists={dermatologists}/></div>;
    
    ReactDOM.render(
      element,
      document.getElementById('tabel-div')
    );
    
  }


    const columns = useMemo(
        () => [
          {
            // first group 
            Header: "Slobodni termini",
            // First group columns
            columns: [
              {
                Header: "Datum",
                accessor: "date"
              },
              {
                Header: "Pocinje",
                accessor: "appointmentStart"
              },
              {
                Header: "Zavrsava se",
                accessor: "appointmentEnd"
              },
              {
                Header: "Trajanje (min)",
                accessor: "duration"
              },
              {
                Header: "Cena (RSD)",
                accessor: "price"
              },
              {
                Header: "Dermatolog",
                accessor: "dermatologistId"
              }
            ]
          }
        ],
        []
      );
    
    return(
        <div>
          <h1>Mozete otkazati termin klikom na termin</h1>
          <form>
          <label>Sortiraj po </label>
          <select def onChange={(val) => handlePeriodChange(val.target.value)}>
          <option disabled selected value> </option>
          <option value="cenaAsc">ceni (asc)</option>
          <option value="cenaDecs">ceni (desc)</option>
          </select>
          </form>
          <div id="tabel-div"><Table id="table1" columns={columns} data={data} history={history}  dermatologists={dermatologists}/></div>
          
        </div>
    );

}