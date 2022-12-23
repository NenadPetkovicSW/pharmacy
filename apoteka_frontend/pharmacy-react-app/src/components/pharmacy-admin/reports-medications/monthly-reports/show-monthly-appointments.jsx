import React, { Component, Fragment, useState } from 'react';
import { useEffect } from 'react';
import BarChart from './charts';
import '../reports.css';

export default function MonthlyReportsMedication(){
    const [chartData,setChartData] = useState({});

    const getChartData = () => {
        // Ajax calls here
        setChartData({
          chartData:{
            labels: ['January', 'February', 'March', 'April','May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
            datasets:[
              {
                label:'Number of appointments',
                data:[
                  10,
                  20,
                  15,
                  10,
                  5,
                  30,
                  30,
                  20,
                  15,
                  10,
                  2,
                  12,
                ],
                backgroundColor:[
                  'rgba(255, 99, 132, 0.6)',
                  'rgba(54, 162, 235, 0.6)',
                  'rgba(255, 206, 86, 0.6)',
                  'rgba(75, 192, 192, 0.6)',
                  'rgba(153, 102, 255, 0.6)',
                  'rgba(255, 159, 64, 0.6)',
                  'rgba(255, 99, 132, 0.6)',
                  'rgba(255, 99, 10, 0.6)',
                  'rgba(54, 220, 235, 0.6)',
                  'rgba(255, 206, 120, 0.6)',
                  'rgba(75, 230, 192, 0.6)',
                  'rgba(103, 102, 255, 0.6)',
                ]
              }
            ]
          }
        });
      }

    useEffect(()=>{
        getChartData();
    },[]);

    return (
        <>
        <Fragment>
          <BarChart title={'Medication Purchases Monthly'}></BarChart>
        </Fragment>
        </>
    );
}

