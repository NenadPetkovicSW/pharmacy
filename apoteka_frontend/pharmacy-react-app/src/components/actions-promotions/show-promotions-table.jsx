import React, { useMemo, useState, useEffect } from "react";
import $ from 'jquery';
import '../css/ItemCardPromotion.css'
import { useHistory } from "react-router-dom";
import promotionsService from "../common/promotion-action-axios/promotions-service";
import userService from "../common/user-axios/user-service";

export default function PromotionsTable(){
    const [data, setData] = useState([]);
    const [clickedButton, setClicked] = useState();
    const history = useHistory();
    // Using useEffect to call the API once mounted and set the data
    useEffect(() => {
        let id = window.location.pathname.split('/')[3];
        console.log(id);
        promotionsService.getAll(id)
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
              var myCol = $('<div class="row center-cols center-align id="row'+ String(data[i].id)+'""></div>');
              let myButton = `<button id="`+String(data[i].id)+`" position="left" type="button">Prijavi se za promociju</button>`;
              var myPanel = $('<div class="property-card" id="Card'+String(data[i].id)+'"><div class="property-image"></div><div class="property-title"><span>Ime promocije: '
              +String(data[i].name)+'</span><p>Opis: '+String(data[i].description)+' </p><p>Kolicina: '+String(data[i].amountOfProduct)+' </p>'+myButton+'</div></div>');
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
      let id =  localStorage.getItem('LogedUser').split(',')[0].split(':')[1];
      if(clickedButton !== undefined){
        console.log(clickedButton);
        userService.addPromotion(clickedButton,id)
        .then(response => {
            console.log("Response" + response.data);
        })
        .catch(e => {
            console.log("Hewwo" + e);
        }); 
     }
        
    },[clickedButton]);

      
    
    return(
      <div class="container">
        <div class="row" id="contentPanel">
        </div>
      </div>
    );

}