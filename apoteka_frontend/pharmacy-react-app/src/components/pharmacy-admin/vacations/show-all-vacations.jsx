import medicationsService from "../../common/medications-axios/medications-service";
import vacationService from "../../common/vacation-paid-leave-axios/vacation-service";
import React, { useMemo, useState, useEffect } from "react";
import Table from "./table-vacation";
import { Fragment } from "react";
import { data } from "jquery";


function VacationsTable({dataTable}){
    const [data, setData] = useState(dataTable);
    // Using useEffect to call the API once mounted and set the data

    const columns = useMemo(
        () => [
          {
            // first group 
            Header: "Vacation requests",
            // First group columns
            columns: [
              {
                Header: "Is pharmacist?",
                accessor: d => d.pharmacist.toString()
              },
              {
                Header: "Employee id",
                accessor: "employeeId"
              },
            ]
          }
        ],
        []
      );
    
    return(
        <Fragment>
        {(data.length == 0)? (''):
            (<Table columns={columns} data={data} /> )
        }
        </Fragment>
    );
}

export default function VacationsHomePage(){
    const [loaded, setLoaded] = useState(false);
    const [vacations, setVacations] = useState([]);
    const pharmacyId = localStorage.getItem('LogedUser').split(',')[0].split(':')[1];
    
    useEffect(() => {
        //imamo sve medications za tu apoteku
        vacationService.getAll(pharmacyId)
        .then(response => {
                setVacations(response.data);
                setLoaded(true);
            })
        .catch(e => {
            console.log(e);
        });
    }, []);

    
    return(
        <Fragment>
            {loaded?
                <VacationsTable dataTable={vacations}/>:''}
        </Fragment>
        
    );

}