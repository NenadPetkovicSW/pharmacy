import React, {Component} from 'react';
import { useState } from 'react';

function BarGroup(props) {
  let barPadding = 2
  let barColour = '#b19cd9'
  let widthScale = d => d 

  let width = widthScale(props.d.value)
  let yMid = props.barHeight * 0.5
  
  return <g className="bar-group">
    <text className="name-label" x="-6" y={yMid} alignmentBaseline="middle" >{props.d.name}</text>
    <rect y={barPadding * 0.5} width={width} height={props.barHeight - barPadding} fill={barColour} />
    <text className="value-label" x={width- 8} y={yMid} alignmentBaseline="middle" >{props.d.value}</text>
  </g>
}

class BarChart extends React.Component {
  state = {
    data: [
      { name: '2019', value: 200 },
      { name: '2020', value: 600 },
      { name: '2021', value: 800 },
      { name: '2022', value: 400 },
    ]
  }

  render() {
    let barHeight = 30
        
    let barGroups = this.state.data.map((d, i) => <g transform={`translate(0, ${i * barHeight})`}>
                                                    <BarGroup d={d} barHeight={barHeight} />
                                                  </g>)                         
    
    return <svg width="1000" height="200" >
      <g className="container">
        <text className="title" x="10" y="30">{this.props.title}</text>
        <g className="chart" transform="translate(100,60)">
          {barGroups}
        </g>
      </g>
    </svg>
  }
}

export default BarChart;
