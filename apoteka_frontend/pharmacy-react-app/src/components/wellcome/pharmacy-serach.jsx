import pharmaciesService from "../common/pharmacies-axios/pharmacies-service";
import React, { useMemo, useState, useEffect } from "react";
import $ from 'jquery';
import '../css/ItemCard.css'
import { useHistory } from "react-router-dom";

export default function PharmacySearch(){
    const [pharmacies, setPharmacies] = useState([]);
    const [dataLodaed, setDataLoader] = useState(false);
    const history = useHistory();
    const [name, setName] = useState("");
    const [address, setAddress] = useState("");

    // Using useEffect to call the API once mounted and set the data
    useEffect(() => {
        pharmaciesService.getAll()
        .then(response => {
            setPharmacies(response.data);
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
        for (let i = 0; i<pharmacies.length; i++) {
            var myCol = $('<div class="row center-cols center-align id="row'+String(pharmacies[i].id)+'""></div>');
            var myPanel = $('<div class="property-card" id="Card'+String(pharmacies[i].id)+'"><div class="property-image"></div><div class="property-title"><span>'+String(pharmacies[i].name)+'</span><p>'+String(pharmacies[i].address)+' </p><p>Score: '+String(pharmacies[i].averageScore)+' </p></div></div>');
            myPanel.appendTo(myCol);
            myCol.appendTo('#contentPanel');
        }
        
        
        
        
    };
    
    $(document).ready(function(){
        addCols(pharmacies.length);
        return false;
    });



},[dataLodaed]);

function OnClick(e)
{
    for (let i = 0; i<pharmacies.length; i++) {
        $("#Card"+String(pharmacies[i].id)).show();
        $("#row"+String(pharmacies[i].id)).show();
    }
    for (let i = 0; i<pharmacies.length; i++) {
        if(String(pharmacies[i].name)!=name && name!="")
        {
            $("#Card"+String(pharmacies[i].id)).hide();
            $("#row"+String(pharmacies[i].id)).hide();
        }
        if(String(pharmacies[i].address)!=address && address!="")
        {
            $("#Card"+String(pharmacies[i].id)).hide();
            $("#row"+String(pharmacies[i].id)).hide();
        }
    }
   
}
function handleName(e)
{
    setName(document.getElementById("name").value);
}
function handleAddress(e)
{
    setAddress(document.getElementById("address").value);
}
      
        return (
            <div class="container">
                <div class="form-div" id="left">
                <form class="search-form">
                    <label id="label-search">Ime</label>
                    <input id="name"  type="text" class="input-search" onChange={handleName}/>
                    <label id="label-search">Adresa</label>
                    <input id="address" type="text" class="input-search" onChange={handleAddress}/>
                    <button id="button-search" type="button" onClick={OnClick}></button>
                </form>
                </div>
                <br />
                <div class="row" id="contentPanel">

                </div>
            </div>

        )
};