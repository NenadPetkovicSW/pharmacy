import medicationsService from "../../common/medications-axios/medications-service";
import paidLeaveService from "../../common/vacation-paid-leave-axios/paid-leave-service";
import React, { useMemo, useState, useEffect } from "react";
import Table from "./table-paid-leave";
import { Fragment } from "react";
import { data } from "jquery";


function PaidLeaveTable({dataTable}){
    const [data, setData] = useState(dataTable);
    // Using useEffect to call the API once mounted and set the data

    const columns = useMemo(
        () => [
          {
            // first group 
            Header: "Paid Leave requests",
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

export default function PaidLeaveHomePage(){
    const [loaded, setLoaded] = useState(false);
    const [paidLeaves, setPaidLeaves] = useState([]);
    const pharmacyId = localStorage.getItem('LogedUser').split(',')[0].split(':')[1];
    
    useEffect(() => {
        //imamo sve medications za tu apoteku
        paidLeaveService.getAll(pharmacyId)
        .then(response => {
                setPaidLeaves(response.data);
                setLoaded(true);
            })
        .catch(e => {
            console.log(e);
        });
    }, []);

    
    return(
        <Fragment>
            {loaded?
                <PaidLeaveTable dataTable={paidLeaves}/>:''}
        </Fragment>
        
    );

}