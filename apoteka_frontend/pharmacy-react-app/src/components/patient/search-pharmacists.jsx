import service from "../common/pharmacists-axios/pharmacists-service";
import React, { useMemo, useState, useEffect } from "react";
import pharmacyService from "../common/pharmacies-axios/pharmacies-service";
import $ from 'jquery';
import '../css/ItemCard.css'
import { useHistory } from "react-router-dom";

export default function PharmacistsSearch(){
    const [pharmacists, setPharmacists] = useState([]);
    const history = useHistory();
    const [name, setName] = useState("");
    const [lastName, setLastName] = useState("");
    const [temp, setTempName] = useState("");
    const [pharmacyId, setPharmacyId]=useState("");

    // Using useEffect to call the API once mounted and set the data
    useEffect(() => {
        service.getAll()
        .then(response => {
            setPharmacists(response.data);
                console.log(response.data);
            })
        .catch(e => {
            console.log(e);
        });
    },[]);

    useEffect(() => {
    document.getElementById("contentPanel").innerHTML = "";
    var addCols = function (num){
        for (let i = 0; i<pharmacists.length; i++) {
            pharmacyService.get(pharmacists[i].pharmacy)
            .then(response => {
                var myCol = $('<div class="row center-cols center-align id="row'+String(pharmacists[i].id)+'""></div>');
                var myPanel = $('<div class="property-card-search" id="Card'+String(pharmacists[i].id)+'"><div class="property-image-search"></div><div class="property-title-search"><span>'+String(pharmacists[i].firstName)+'</span><p>'+String(pharmacists[i].lastName)+' </p><p>'+String(response.data.name)+' </p></div></div>');
                myPanel.appendTo(myCol);
                myCol.appendTo('#contentPanel');
                })
            .catch(e => {
                console.log(e);
            }); 
        }   
    };
    
    $(document).ready(function(){
        addCols(pharmacists.length);
        return false;
    });
    },[pharmacists]);

    function OnClick(e)
    {
        if(lastName == '')
            setLastName("0");
        if(name == '')
            setName("0");
        if(pharmacyId == '') 
            setPharmacyId("0");
        if(lastName == 0 && name==0 && pharmacyId==0){
            service.getAll()
            .then(response => {
                setPharmacists(response.data);
                    console.log(response.data);
                })
            .catch(e => {
                console.log(e);
            });
        }
        else{ 
            service.getAllSearch(name,lastName,pharmacyId).then(response => {
                setPharmacists(response.data);
                console.log(response.data);
                })
            .catch(e => {
                console.log(e);
            });
        }   
        
        
    }
    function handleName(e)
    {
        setName(document.getElementById("name").value);
    }
    function handleLastName(e)
    {
        setLastName(document.getElementById("lastname").value);
    }
    function handlePharmacy(e)
    {
        setPharmacyId(document.getElementById("pharmacy").value);
    }
      
    return (
        <div class="container">
            <div class="form-div" id="left">
            <form class="search-form">
                <label id="label-search">Ime</label>
                <input id="name"  type="text" class="input-search" onChange={handleName}/>
                <label id="label-search">Prezime</label>
                <input id="lastname" type="text" class="input-search" onChange={handleLastName}/>
                <label id="label-search">Ocena (donja):</label>
                <input id="ocenaVeca" type="text" class="input-search" onChange={null}/>
                <label id="label-search">Ocena (gornja):</label>
                <input id="ocenaManja" type="text" class="input-search" onChange={null}/>
                <label id="label-search">Id apoteke:</label>
                <input id="pharmacy" type="text" class="input-search" onChange={handlePharmacy}/>
                <button id="button-search" type="button" onClick={OnClick}></button>
            </form>
            </div>
            <br />
            <div class="row" id="contentPanel">

            </div>
        </div>

    )
};