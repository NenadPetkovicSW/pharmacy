import React, { Component, Fragment, useState } from 'react';
import { useEffect } from 'react';
import BarChart from './charts';
import '../reports.css';

export default function QuarterlyReports(){
    const [chartData,setChartData] = useState({});
    return (
        <>
        
        <Fragment>
          <BarChart title={'Dermatologist Quarterly appointments'}></BarChart>
        </Fragment>
        <Fragment>
        
          <BarChart title={'Pharmacists Quarterly appointments'}></BarChart>
        </Fragment>
        </>
    );
}

