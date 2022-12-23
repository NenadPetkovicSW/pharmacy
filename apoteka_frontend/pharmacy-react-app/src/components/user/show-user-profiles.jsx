import React, { useMemo, useState, useEffect } from "react";
import Table from "./table";
import userServices from '../common/user-axios/user-service';


export default function ShowProfiles(){
    const [data, setData] = useState([]);

    // Using useEffect to call the API once mounted and set the data
    useEffect(() => {
        userServices.getAll()
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
            Header: "Profile",
            // First group columns
            columns: [
              {
                Header: "Id",
                accessor: "id"
              },
              {
                Header: "First name",
                accessor: "firstName"
              },
              {
                Header: "Last name",
                accessor: "lastName"
              },
              {
                Header: "Email",
                accessor: "email"
              },
              {
                Header: "Username",
                accessor: "username"
              },
              {
                Header: "Password",
                accessor: "password"
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