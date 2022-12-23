import React, { Component, Fragment, useState } from 'react';
import { useEffect } from 'react';
import BarChart from './charts';
import '../reports.css';

export default function QuarterlyReportsMedication(){
    const [chartData,setChartData] = useState({});
    return (
        <>
        
        <Fragment>
          <BarChart title={'Medication Purchases  Quarterly'}></BarChart>
        </Fragment>
        </>
    );
}

