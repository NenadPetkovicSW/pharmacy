import React, {Component} from 'react';
import { useState } from 'react';

function BarGroup(props) {
  let barPadding = 2
  let barColour = '#8FBC8F'
  let widthScale = d => d * 6

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
      { name: 'Q1', value: 120 },
      { name: 'Q2', value: 140 },
      { name: 'Q3', value: 135 },
      { name: 'Q4', value: 150 },
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
