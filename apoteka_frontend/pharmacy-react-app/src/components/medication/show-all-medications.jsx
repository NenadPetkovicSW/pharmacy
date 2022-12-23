import medicationsService from "../common/medications-axios/medications-service";
import React, { useMemo, useState, useEffect } from "react";
import Table from "./table";

export default function MedicationTable({id}){
    const [data, setData] = useState([]);

    // Using useEffect to call the API once mounted and set the data
    useEffect(() => {
        id == null ? 
        medicationsService.getAll()
        .then(response => {
                setData(response.data);
                console.log(response.data);
              })
        .catch(e => {
            console.log(e);
        }) : 
        medicationsService.getByPharmacyId(id)
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
            Header: "Medication",
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
          },
          {
            // Second group - Details
            Header: "Details",
            // Second group columns
            columns: [
              {
                Header: "Alergens"
              },
              {
                Header: "Price",
                accessor: "price"
              },
              {
                Header: "Date",
                accessor: "newPriceDate"
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