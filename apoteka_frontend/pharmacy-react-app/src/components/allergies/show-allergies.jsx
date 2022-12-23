import React, { useMemo, useState, useEffect } from "react";
import Table from "./table";
import allergieServices from '../common/allergies-axios/allergies-service';


export default function ShowAlergies(){
    const [data, setData] = useState([]);

    // Using useEffect to call the API once mounted and set the data
    useEffect(() => {
      allergieServices.getAll()
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
            Header: "Alergije",
            // First group columns
            columns: [
              {
                Header: "Id",
                accessor: "id"
              },
              {
                Header: "Name",
                accessor: "name"
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