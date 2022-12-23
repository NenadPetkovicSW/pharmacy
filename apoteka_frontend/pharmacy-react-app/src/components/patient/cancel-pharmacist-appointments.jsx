import DerApoServise from "../common/appointment-axios/pharmacist-appointment-service"
import ReactDOM from 'react-dom';
import React, { useMemo, useState, useEffect } from "react";
import Table from "./table-cancel-appointment-pharmacist";
import { useHistory } from "react-router-dom";

export default function AppointmentsDermatologistsTable(){
  
    const [data, setData] = useState([]);
    const history = useHistory();

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

    let element =  <div id="tabel-div"><Table id="table1" columns={columns} data={data} history={history}/></div>;
    
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
          <div id="tabel-div"><Table id="table1" columns={columns} data={data} history={history}/></div>
          
        </div>
    );

}