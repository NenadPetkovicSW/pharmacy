import pharmacistService from "../common/pharmacists-axios/pharmacists-service";
import React, { useMemo, useState, useEffect } from "react";
import $ from 'jquery';
import '../css/ItemCard.css'
import { useHistory } from "react-router-dom";

export default function FreePharmacistSearch(){
    const [pharmacists, setPharmacists] = useState([]);
    const [dataLodaed, setDataLoader] = useState(false);
    const history = useHistory();

    const [name, setName] = useState("");
    

    var reply_click = function()
    {
                history.push(`/pharmacist-appointments/${this.id}`);
    }

    // Using useEffect to call the API once mounted and set the data
    useEffect(() => {
        pharmacistService.getFreePharmacist(window.location.pathname.split('/')[2])
        .then(response => {
            setPharmacists(response.data);
            setDataLoader(true);
                console.log(response.data);
              })
        .catch(e => {
            console.log(e);
        });
    }, []);

    useEffect(() => {
    document.getElementById("contentPanel").innerHTML = "";
    var addCols = function (num){
        for (let i = 0; i<pharmacists.length; i++) {
            var myCol = $('<div class="row center-cols center-align id="row'+String(pharmacists[i].id)+'""></div>');
            let myButtom = '<button class="button-pharmacy" id="'+pharmacists[i].id+'"position="left" type="button">Pogledaj termine</button>';
            var myPanel = $('<div class="property-card" id="Card'+String(pharmacists[i].id)+'"><div class="property-image-pharmacist"></div><div class="property-title"><span>'+String(pharmacists[i].firstName + " " + pharmacists[i].lastName)+'</span><p>Score: 0 </p>'+myButtom+'</div></div>');
            myPanel.appendTo(myCol);
            myCol.appendTo('#contentPanel');
            
            
            
            document.getElementById(String(pharmacists[i].id)).onclick = reply_click;
        }
        
        
        
        
    };
    
    $(document).ready(function(){
        addCols(pharmacists.length);
        return false;
    });



},[dataLodaed]);

function OnClick(e)
{
    for (let i = 0; i<pharmacists.length; i++) {
        $("#Card"+String(pharmacists[i].id)).show();
        $("#row"+String(pharmacists[i].id)).show();
    }
    for (let i = 0; i<pharmacists.length; i++) {
        if(String(pharmacists[i].firstName + " " + pharmacists[i].lastName )!=name && name!="")
        {
            $("#Card"+String(pharmacists[i].id)).hide();
            $("#row"+String(pharmacists[i].id)).hide();
        }
        
    }
   
}




function handleName(e)
{
    setName(document.getElementById("name").value);
}


      
        return (
            <div class="container">
                <div class="form-div" id="left">
                <form class="search-form">
                    <label id="label-search">Ime </label>
                    <input id="name"  class="input-search" type="text"  onChange={handleName}/><br />
                    
                    <button id="button-search" type="button" onClick={OnClick}></button>
                </form>
                </div>
                <br />
                <div class="row" id="contentPanel">

                </div>
            </div>

        )
};