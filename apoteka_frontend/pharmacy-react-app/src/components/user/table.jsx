import React from "react";
import { useTable } from "react-table";
import '../css/pharmacy-table.css';
import { useHistory } from "react-router-dom";

export default function Table({ columns, data }) {
    // Use the useTable Hook to send the columns and data to build the table
    const history = useHistory();
    const {
        getTableProps, // table props from react-table
        getTableBodyProps, // table body props from react-table
        headerGroups, // headerGroups, if your table has groupings
        rows, // rows for the table based on the data passed
        prepareRow // Prepare the row (this function needs to be called for each row before getting the row props)
    } = useTable({
        columns,
        data
    });

    const handleTableCellClick = (e,row) => {
       
        history.push(`/profile/${row.original.id}`);
     }
        
    return (
        <table {...getTableProps()}>
        <thead>
            {headerGroups.map(headerGroup => (
            <tr {...headerGroup.getHeaderGroupProps()}>
                {headerGroup.headers.map(column => (
                <th {...column.getHeaderProps()}>{column.render("Header")}</th>
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
                    return <td {...cell.getCellProps({onClick: (e)=>handleTableCellClick(e,row)})}>
                                {cell.render("Cell")}</td>;
                })}
                </tr>
            );
            })}
        </tbody>
        </table>
    );
}