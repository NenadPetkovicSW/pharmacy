import React, { Component, Fragment, useState } from 'react';
import { useEffect } from 'react';
import BarChart from './charts';
import '../reports.css';

export default function YearlyReports(){
    const [chartData,setChartData] = useState({});
    return (
        <>
        
        <Fragment>
          <BarChart title={'Dermatologist Yearly appointments'}></BarChart>
        </Fragment>
        <Fragment>
        
          <BarChart title={'Pharmacists Yearly appointments'}></BarChart>
        </Fragment>
        </>
    );
}

