import React, { useEffect, useState } from "react";
import { useTable } from "react-table";
import '../css/pharmacy-table.css';
import AppDerServ from "../common/appointment-axios/dermatologists-appointment-service"
import { useHistory } from "react-router-dom";
import Derserv from "../common/dermatologists-axios/dermatologists-service"
export default function Table({ columns, data , history, dermatologists}) {
    // Use the useTable Hook to send the columns and data to build the table
    var thisHistory = useHistory(history);
    const [derma, setDerma] = useState([]);

    useEffect(() => {

        Derserv.getAll()
        .then(response => {
                setDerma(response.data);
                console.log(response.data);
                
              })
        .catch(e => {
            console.log(e);
        });
  
  
    }, []);

    dermatologists = derma;

    for(var i = 0; i < data.length; i++)
    {
        let idDerm = Number(data[i].dermatologistId);

        for(var j=0;j<dermatologists.length;j++)
        {
            let id = Number(dermatologists[j].id);

            if(id==idDerm)
            {
                let first = String(dermatologists[j].firstName);
                let last = String(dermatologists[j].lastName);

                data[i].dermatologistId = String(first + " " + last);
                break;
            }
        }
    }

    for(var i = 0; i < data.length; i++) {
        
        let date = new Date(String(data[i].date));
        let ye = new Intl.DateTimeFormat('en', { year: 'numeric' }).format(date);
        let mo = new Intl.DateTimeFormat('en', { month: 'short' }).format(date);
        let da = new Intl.DateTimeFormat('en', { day: '2-digit' }).format(date);
        data[i].date = `${da}-${mo}-${ye}`;
    }

    const {
        getTableProps, // table props from react-table
        getTableBodyProps, // table body props from react-table
        headerGroups, // headerGroups, if your table has groupings
        rows, // rows for the table based on the data passed
        prepareRow // Prepare the row (this function needs to be called for each row before getting the row props)
    } = useTable({
        columns,
        data,
    });
    
    const handleTableCellClick = (e,row) => {
        
        alert("Zakazano");
        let pomocna = JSON.parse(localStorage.getItem('LogedUser'));

        AppDerServ.makeAppointment(row.original.id,pomocna.id)
        .then(response => {
              
              console.log(response.data);
             
            })
        .catch(e => {
          console.log(e);
      })
      window.location.reload();
      
    }
    
    return (
        <table id="myTable" {...getTableProps()}>
        <thead>
            {headerGroups.map(headerGroup => (
            <tr {...headerGroup.getHeaderGroupProps()}>
                {headerGroup.headers.map(column => (
                <th {...column.getHeaderProps()} >{column.render("Header")}</th>
                ))}
            </tr>
            ))}
        </thead>
        <tbody {...getTableBodyProps()}>
            {rows.map((row, i) => {
            prepareRow(row); // This line is necessary to prepare the rows and get the row props from react-table dynamically
            return (
                // Each row can be rendered directly as a string using the react-table render method
                <tr {...row.getRowProps()}>
                {row.cells.map(cell => {
                    return <td 
                        {...cell.getCellProps({onClick: (e)=>handleTableCellClick(e,row)})}>
                                {
                                cell.render("Cell")
                                }
                        </td>
                        
                })}
                </tr>
            );
            })}
        </tbody>
        </table>
    );
}