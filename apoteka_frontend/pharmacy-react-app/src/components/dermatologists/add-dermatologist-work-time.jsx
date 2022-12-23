import workTimeService from "../common/dermatologists-axios/dermatologists-work-time-service"

import React, { useMemo, useState, useEffect } from "react";
import Table from "./dermatologist-work-time-table";
import { Fragment } from "react";

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
                Header: "Id",
                accessor: "id"
              },
              {
                Header: "Shift start",
                accessor: "shiftStart"
              },
              {
                Header: "Shift End",
                accessor: "shiftEnd"
              },
              {
                Header: "Pharmacy Id",
                accessor: "pharmacyId"
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

export default function AddWorkTimeDermatologists(){
    const [data1, setData] = useState([]);
    const [loaded, setLoaded] = useState(false);
    const [submitted, setSubmitted] = useState(false);
    const [startTime, setStartTime] = useState("");
    const [endTime, setEndTime] = useState("");
    const pharmacyId = localStorage.getItem('LogedUser').split(',')[0].split(':')[1];
    const dermatologistId = window.location.pathname.split('/')[2];
    const [error, setError] = useState({
        active: false,
        message:  "Nedozvoljeno vreme!"
    });
    // Using useEffect to call the API once mounted and set the data
    useEffect(() => {
        workTimeService.getAll(window.location.pathname.split('/')[2])
        .then(response => {
                setData(data1.concat(response.data));
                setLoaded(true);
            })
        .catch(e => {
            console.log(e);
        });
    }, [loaded]);

    const onChangeEndTime = (e) => {
        setEndTime(e.target.value);
    }

    const onChangeStartTime = (e) => {
        setStartTime(e.target.value);
    }

    const saveWorkTime = (e) => {
        var data = {
            id: null,
            shiftStart: startTime,
            shiftEnd: endTime,
            pharmacyId: pharmacyId,
            dermatologistId: dermatologistId
        };
        data1.forEach(function (arrayItem) {

            if(arrayItem.shiftStart < startTime && endTime < arrayItem.shiftEnd){
                setError({active:true});
                return;
            }
            if(arrayItem.shiftStart < endTime && startTime < arrayItem.shiftEnd){
                setError({active:true});
                return;
            }
        });
        if(error.active) {return;}
        workTimeService.create(data)
          .then(response => {
            setSubmitted(true);
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    }

    const newWorkTime = () => {
        setLoaded(false);
        setSubmitted(false);
        setStartTime("");
        setEndTime("");
        setData([]);
        setError({
            active:false
        })
    }

    
    return(
        <Fragment>
            {loaded ? <WorkTimeDermatologistsTable dataTable={data1} /> : ''}
            <div className="submit-form">
                {submitted && !error.active? (
                <div>
                    <h4>You submitted successfully!</h4>
                    <button className="btn btn-success" onClick={newWorkTime}>
                    Add
                    </button>
                </div>
                ) : (    
                <div>
                    <div className="form-group">
                    <label htmlFor="lastName">Start time (HH:mm:ss)</label>
                    <input
                        type="text"
                        className="form-control"
                        id="startTime"
                        required
                        value={startTime}
                        onChange={onChangeStartTime}
                        name="startTime"
                    />
                    </div>
                    <div className="form-group">
                    <label htmlFor="lastName">End time (HH:mm:ss)</label>
                    <input
                        type="text"
                        className="form-control"
                        id="endTime"
                        required
                        value={endTime}
                        onChange={onChangeEndTime}
                        name="endTime"
                    />
                    </div>
        
                    <button onClick={saveWorkTime} className="btn btn-success">
                    Submit
                    </button>
                    
                </div>
                )}
            </div>
            <Fragment>
                     {error.active? "Nedozvoljeno vreme!" : ""}
            </Fragment>
        </Fragment>
        
    );

}