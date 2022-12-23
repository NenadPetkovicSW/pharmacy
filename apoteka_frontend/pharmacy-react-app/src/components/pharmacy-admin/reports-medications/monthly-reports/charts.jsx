import React, {Component} from 'react';
import { useState } from 'react';

function BarGroup(props) {
  let barPadding = 2
  let barColour = '#348AA7'
  let widthScale = d => d * 10

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
      { name: 'Jan', value: 20 },
      { name: 'Feb', value: 40 },
      { name: 'Mar', value: 35 },
      { name: 'Apr', value: 50 },
      { name: 'May', value: 55 },
      { name: 'Jun', value: 40 },
      { name: 'Jul', value: 30 },
      { name: 'Aug', value: 31 },
      { name: 'Sep', value: 20 },
      { name: 'Oct', value: 33 },
      { name: 'Nov', value: 22 },
      { name: 'Dec', value: 12 }
    ]
  }

  render() {
    let barHeight = 30
        
    let barGroups = this.state.data.map((d, i) => <g transform={`translate(0, ${i * barHeight})`}>
                                                    <BarGroup d={d} barHeight={barHeight} />
                                                  </g>)                         
    
    return <svg width="800" height="500" >
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
