import pharmaciesService from "../common/pharmacies-axios/pharmacies-service";
import React, { useMemo, useState, useEffect } from "react";
import $ from 'jquery';
import '../css/ItemCard.css'
import { useHistory } from "react-router-dom";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

export default function FreePharmacySearch(){
    const [pharmacies, setPharmacies] = useState([]);
    const [dataLodaed, setDataLoader] = useState(false);
    const history = useHistory();
    var datNow = new Date(Date.now());
    const [date, setDate] = useState(new Date(datNow.getFullYear(),datNow.getMonth(),datNow.getDay(),0,0,0,0));
    const [from, setFrom] = useState(new Date(0, 0, 0, 0, 0, 0, 0));
    const [to, setTo] = useState(new Date(0, 0, 0, 23, 59, 0, 0));
    const [refe,SetRefe] = useState(true);

    var reply_click = function()
    {
        history.push(`/free-pharmacy-pharmacist/${this.id}`);
    }

    useEffect(() => {
        pharmaciesService.getFree()
        .then(response => {
            setPharmacies(response.data);
            setDataLoader(!dataLodaed);
                console.log(response.data);
              })
        .catch(e => {
            console.log(e);
        });
    }, [refe]);

    useEffect(() => {
    document.getElementById("contentPanel").innerHTML = "";
    var addCols = function (num){
        for (let i = 0; i<pharmacies.length; i++) {
            var myCol = $('<div class="row center-cols center-align id="row'+String(pharmacies[i].id)+'""></div>');
            let myButtom = '<button class="button-pharmacy" id="'+pharmacies[i].id+'"position="left" type="button">Farmaceuti</button>';
            var myPanel = $('<div class="property-card" id="Card'+String(pharmacies[i].id)+'"><div class="property-image"></div><div class="property-title"><span>'+String(pharmacies[i].name)+'</span><p>'+String(pharmacies[i].address)+' </p><p>Score: '+String(pharmacies[i].averageScore)+' </p>'+myButtom+'</div></div>');
            myPanel.appendTo(myCol);
            myCol.appendTo('#contentPanel');
            
            
            
            document.getElementById(String(pharmacies[i].id)).onclick = reply_click;
        }
        
        
        
        
    };
    
    $(document).ready(function(){
        addCols(pharmacies.length);
        return false;
    });



},[dataLodaed]);

function OnClick(e)
{
        pharmaciesService.getFreePharmaciestByDateAndTime(date.getTime(),from.getTime(),to.getTime())
        .then(response => {
            setPharmacies(response.data);
            setDataLoader(!dataLodaed);
                console.log(response.data);
              })
        .catch(e => {
            console.log(e);
        });
}

function refresh(e)
{
    
    SetRefe(!refe);
}
function handleFrom(e)
{
    let hours = Number(String(document.getElementById("from").value).split(":")[0]);
    let min = Number(String(document.getElementById("from").value).split(":")[1]);

    setFrom(new Date(0, 0, 0, hours, min, 0, 0))
}
function handleTo(e)
{
    let hours = Number(String(document.getElementById("to").value).split(":")[0]);
    let min = Number(String(document.getElementById("to").value).split(":")[1]);

    setTo(new Date(0, 0, 0, hours, min, 0, 0))
}

      
        return (
            <div class="container">
                <div class="form-div" id="left">
                <form class="search-form">
                    <label id="label-search">Datum </label>
                    <DatePicker selected={date} onChange={date1 => setDate(date1)} />
                    <label id="label-search">From </label>
                    <input id="from" class="input-search" type="time" onChange={handleFrom}/><br />
                    <label id="label-search">To </label>
                    <input id="to"  class="input-search" type="time" onChange={handleTo}/><br />
                    <button id="button-search" type="button" onClick={OnClick}></button>
                    <button id="button-refresh" type="button" onClick={refresh}></button>
                </form>
                </div>
                <br />
                <div class="row" id="contentPanel">

                </div>
            </div>

        )
};