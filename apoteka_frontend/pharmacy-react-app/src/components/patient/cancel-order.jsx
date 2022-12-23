import MedOrderService from "../common/medications-axios/medication-user-order"
import ReactDOM from 'react-dom';
import React, { useMemo, useState, useEffect } from "react";
import Table from "./table-cancel-order";
import MedService from "../common/medications-axios/medications-service"
import PharService from "../common/pharmacies-axios/pharmacies-service"
import { useHistory } from "react-router-dom";

export default function AppointmentsDermatologistsTable(){
  
    const [data, setData] = useState([]);
    const [medication, setMedication] = useState([]);
    const [pharmacy, setPharmacy] = useState([]);
    const history = useHistory();

    // Using useEffect to call the API once mounted and set the data
    useEffect(() => {
      let pomocna = JSON.parse(localStorage.getItem('LogedUser'));
      MedOrderService.getOrderByUser(pomocna.id)
      .then(response => {
              setData(response.data);
              console.log(response.data);
              
            })
      .catch(e => {
          console.log(e);
      });


  }, []);

  useEffect(() => {
    MedService.getAll()
    .then(response => {
            setMedication(response.data);
            console.log(response.data);
            
          })
    .catch(e => {
        console.log(e);
    });


}, []);

useEffect(() => {
  PharService.getAll()
  .then(response => {
          setPharmacy(response.data);
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

    let element =  <div id="tabel-div"><Table id="table1" columns={columns} pharmacy={pharmacy} medication={medication} data={data} history={history}/></div>;
    
    ReactDOM.render(
      element,
      document.getElementById('tabel-div')
    );
    
  }


    const columns = useMemo(
        () => [
          {
            // first group 
            Header: "Naručeni lekovi",
            // First group columns
            columns: [
              {
                Header: "Podići do",
                accessor: "deliveryDate"
              },
              {
                Header: "Datum rezervisanja",
                accessor: "reservationDate"
              },
              {
                Header: "Cena RSD",
                accessor: "price"
              },
              {
                Header: "Količina",
                accessor: "amount"
              },
              {
                Header: "Lek",
                accessor: "medicationId"
              },
              {
                Header: "Apoteka",
                accessor: "pharmacyId"
              }
            ]
          }
        ],
        []
      );
    
    return(
        <div>
          <h1>Mozete otkazati klikom na narudžbinu</h1>
          <form>
          <label>Sortiraj po </label>
          <select def onChange={(val) => handlePeriodChange(val.target.value)}>
          <option disabled selected value> </option>
          <option value="cenaAsc">ceni (asc)</option>
          <option value="cenaDecs">ceni (desc)</option>
          </select>
          </form>
          <div id="tabel-div"><Table id="table1" pharmacy={pharmacy} columns={columns} medication={medication} data={data} history={history}/></div>
          
        </div>
    );

}