import service from "../common/pharmacists-axios/pharmacists-service";
import pharmacyService from "../common/pharmacies-axios/pharmacies-service";
import React, { useMemo, useState, useEffect } from "react";
import $ from 'jquery';
import '../css/ItemCard.css'
import { useHistory } from "react-router-dom";

export default function PharmacistsSearch(){
    const [pharmacists, setPharmacists] = useState([]);
    const history = useHistory();
    const [name, setName] = useState("");
    const [lastName, setLastName] = useState("");
    const [temp, setTempName] = useState("");

    // Using useEffect to call the API once mounted and set the data
    useEffect(() => {
        var userId = localStorage.getItem('LogedUser').split(',')[0].split(':')[1];  
        service.getAllSearch("0","0",userId)
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
                console.log(response.data);
                var myCol = $('<div class="row center-cols center-align id="row'+String(pharmacists[i].id)+'""></div>');
                var myPanel = $('<div class="property-card-search" id="Card'+String(pharmacists[i].id)+'"><div class="property-image-search"></div><div class="property-title-search"><span>'+String(pharmacists[i].firstName)+'</span><p>'+String(pharmacists[i].lastName)+' </p>' + '<p>'+String(response.data.name)+' </p></div></div>');
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
        if(lastName == 0 && name==0){
            service.getAllSearch(name,lastName,userId)
            .then(response => {
                setPharmacists(response.data);
                    console.log(response.data);
                })
            .catch(e => {
                console.log(e);
            });
        }
        else{ 
            var userId = localStorage.getItem('LogedUser').split(',')[0].split(':')[1];    
            service.getAllSearch(name,lastName,userId).then(response => {
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
      
    return (
        <div class="container">
            <div class="form-div" id="left">
            <form class="search-form">
                <label id="label-search">Ime</label>
                <input id="name"  type="text" class="input-search" onChange={handleName}/>
                <label id="label-search">Prezime</label>
                <input id="lastname" type="text" class="input-search" onChange={handleLastName}/>
                <label id="label-search">Ocena (donja)</label>
                <input id="ocenaVeca" type="text" class="input-search" onChange={null}/>
                <label id="label-search">Ocena (gornja)</label>
                <input id="ocenaManja" type="text" class="input-search" onChange={null}/>
                <button id="button-search" type="button" onClick={OnClick}></button>
            </form>
            </div>
            <br />
            <div class="row" id="contentPanel">

            </div>
        </div>

    )
};