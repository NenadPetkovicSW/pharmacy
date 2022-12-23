import pharmaciesService from "../common/pharmacies-axios/pharmacies-service";
import React, { useMemo, useState, useEffect } from "react";
import Table from "./table";
import $ from 'jquery';
import '../css/ItemCard.css'
import { useHistory } from "react-router-dom";

export default function PharmacyTable(){
    const [data, setData] = useState([]);
    const [clickedButton, setClicked] = useState();
    const history = useHistory();
    // Using useEffect to call the API once mounted and set the data
    useEffect(() => {
        pharmaciesService.getAll()
        .then(response => {
                setData(response.data);
                console.log(response.data);
              })
        .catch(e => {
            console.log(e);
        });
    }, []);

    useEffect(() => {
      document.getElementById("contentPanel").innerHTML = "";
      var addCols = function (num){
          for (let i = 0; i<data.length; i++) {
              var myCol = $('<div class="row center-cols center-align id="row'+String(data[i].id)+'""></div>');
              let myButton = `<button id="`+String(data[i].id)+`" position="left" type="button">Poseti</button>`;
              var myPanel = $('<div class="property-card" id="Card'+String(data[i].id)+'"><div class="property-image"></div><div class="property-title"><span>'
              +String(data[i].name)+'</span><p>'+String(data[i].address)+' </p><p>Score: '+String(data[i].averageScore)+' </p>'+myButton+'</div></div>');
              myPanel.appendTo(myCol);
              myCol.appendTo('#contentPanel');
              var element = document.getElementById(data[i].id);
              element.addEventListener('click', function() {
                setClicked(data[i].id);
              }, false);
              
          }
      };
      $(document).ready(function(){
          addCols(data.length);
          return false;
      });
    },[data]);

    useEffect(() => {
      if(clickedButton !== undefined)
        history.push(`/pharmacies/${clickedButton}`);
    },[clickedButton]);

      
    
    return(
      <div class="container">
        <div class="row" id="contentPanel">
        </div>
      </div>
    );

}