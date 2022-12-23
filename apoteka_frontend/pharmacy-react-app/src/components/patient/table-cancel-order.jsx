import React, { useEffect,useState } from "react";
import { useTable } from "react-table";
import '../css/pharmacy-table.css';
import MedOrderService from "../common/medications-axios/medication-user-order"
import MedService from "../common/medications-axios/medications-service"
import PharService from "../common/pharmacies-axios/pharmacies-service"
import { useHistory } from "react-router-dom";

export default function Table({ columns, data , history, medication, pharmacy}) {
    // Use the useTable Hook to send the columns and data to build the table
    var thisHistory = useHistory(history);
    const [med, setMed] = useState([]);
    const [phar, setPhar] = useState([]);

    useEffect(() => {

        MedService.getAll()
        .then(response => {
            setMed(response.data);
                console.log(response.data);
                
              })
        .catch(e => {
            console.log(e);
        });
  
  
    }, []);

    useEffect(() => {

        PharService.getAll()
        .then(response => {
            setPhar(response.data);
                console.log(response.data);
                
              })
        .catch(e => {
            console.log(e);
        });
  
  
    }, []);

    medication = med;

    for(var i = 0; i < data.length; i++)
    {
        let idmed = Number(data[i].medicationId);

        for(var j=0;j<medication.length;j++)
        {
            let id = Number(medication[j].id);

            if(id==idmed)
            {
                data[i].medicationId = String(medication[j].name);
                break;
            }
        }
    }

    pharmacy = phar;

    for(var i = 0; i < data.length; i++)
    {
        let idphar = Number(data[i].pharmacyId);

        for(var j=0;j<pharmacy.length;j++)
        {
            let id = Number(pharmacy[j].id);

            if(id==idphar)
            {
                data[i].pharmacyId = String(pharmacy[j].name);
                break;
            }
        }
    }
    for(var i = 0; i < data.length; i++) {
        
        let deliveryDate = new Date(String(data[i].deliveryDate));
        let ye1 = new Intl.DateTimeFormat('en', { year: 'numeric' }).format(deliveryDate);
        let mo1 = new Intl.DateTimeFormat('en', { month: 'short' }).format(deliveryDate);
        let da1 = new Intl.DateTimeFormat('en', { day: '2-digit' }).format(deliveryDate);
        data[i].deliveryDate = `${da1}-${mo1}-${ye1}`;

        let reservationDate = new Date(String(data[i].reservationDate));
        let ye2 = new Intl.DateTimeFormat('en', { year: 'numeric' }).format(reservationDate);
        let mo2 = new Intl.DateTimeFormat('en', { month: 'short' }).format(reservationDate);
        let da2 = new Intl.DateTimeFormat('en', { day: '2-digit' }).format(reservationDate);
        data[i].reservationDate = `${da2}-${mo2}-${ye2}`;
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
        
        
        

        MedOrderService.delete(row.original.id)
        .then(response => {

                alert("Otkazano!");
                window.location.reload();
            })
        .catch(e => {
          console.log(e);
      })
      
      
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