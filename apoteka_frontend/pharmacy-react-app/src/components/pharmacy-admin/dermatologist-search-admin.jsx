import dermatologistsService from "../common/dermatologists-axios/dermatologists-service";
import React, { useMemo, useState, useEffect } from "react";
import $ from 'jquery';
import '../css/ItemCard.css'
import { useHistory } from "react-router-dom";

export default function DermatologistsSearch(){
    const [dermatologists, setDermatologists] = useState([]);
    const history = useHistory();
    const [name, setName] = useState("");
    const [lastName, setLastName] = useState("");

    // Using useEffect to call the API once mounted and set the data
    useEffect(() => {
        var userId = localStorage.getItem('LogedUser').split(',')[0].split(':')[1];  
        dermatologistsService.getAllSearch("0","0",userId)
        .then(response => {
            setDermatologists(response.data);
                console.log(response.data);
            })
        .catch(e => {
            console.log(e);
        });
    },[]);

    useEffect(() => {
    document.getElementById("contentPanel").innerHTML = "";
    var addCols = function (num){
        for (let i = 0; i<dermatologists.length; i++) {
            var myCol = $('<div class="row center-cols center-align id="row'+String(dermatologists[i].id)+'""></div>');
            var myPanel = $('<div class="property-card-search" id="Card'+String(dermatologists[i].id)+'"><div class="property-image-search"></div><div class="property-title-search"><span>'+String(dermatologists[i].firstName)+'</span><p>'+String(dermatologists[i].lastName)+' </p></div></div>');
            myPanel.appendTo(myCol);
            myCol.appendTo('#contentPanel');
        }   
    };
    
    $(document).ready(function(){
        addCols(dermatologists.length);
        return false;
    });
    },[dermatologists]);

    function OnClick(e)
    {
        if(lastName == '')
            setLastName("0");
        if(name == '')
            setName("0");
        if(lastName == 0 && name==0){
            dermatologistsService.getAllSearch(name,lastName,userId)
            .then(response => {
                setDermatologists(response.data);
                    console.log(response.data);
                })
            .catch(e => {
                console.log(e);
            });
        }
        else{
            var userId = localStorage.getItem('LogedUser').split(',')[0].split(':')[1];    
            dermatologistsService.getAllSearch(name,lastName,userId).then(response => {
                setDermatologists(response.data);
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