import DerApoServise from "../common/appointment-axios/dermatologists-appointment-service"

import React, { useMemo, useState, useEffect } from "react";
import Table from "./table";

export default function AppointmentsDermatologistsTable(){
    const [data, setData] = useState([]);
    

    // Using useEffect to call the API once mounted and set the data
    useEffect(() => {
      DerApoServise.getAll()
      .then(response => {
              setData(response.data);
              console.log(response.data);
            })
      .catch(e => {
          console.log(e);
      });
  }, []);

    const columns = useMemo(
        () => [
          {
            // first group 
            Header: "Dermatologist appointment",
            // First group columns
            columns: [
              {
                Header: "Id",
                accessor: "id"
              },
              {
                Header: "Date",
                accessor: "date"
              },
              {
                Header: "Starts at",
                accessor: "appointmentStart"
              },
              {
                Header: "Ends at",
                accessor: "appointmentEnd"
              },
              {
                Header: "Duration (min)",
                accessor: "duration"
              },
              {
                Header: "dermatologistId",
                accessor: "dermatologistId" 
              },
              {
                Header: "patientId",
                accessor: "patientId"
              },
              {
                Header: "pharmacyId",
                accessor: "pharmacyId"
              },
              {
                Header: "Is Done",
                accessor: "done"
              }
              ,
              {
                Header: "Price",
                accessor: "price"
              }
            ]
          }
        ],
        []
      );
    
    return(
        <Table columns={columns} data={data} />
    );

}