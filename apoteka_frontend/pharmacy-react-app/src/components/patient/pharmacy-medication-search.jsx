import MedicationService from "../common/medications-axios/medications-service";
import PharmacyService from "../common/pharmacies-axios/pharmacies-service";
import React, { useMemo, useState, useEffect } from "react";
import $ from 'jquery';
import '../css/ItemCardMed.css'
import { useHistory } from "react-router-dom";

export default function MedicationSearch(){
    const [medication, setMedication] = useState([]);
    const [pharmacies, setPharmacies] = useState([]);
    const [dataLodaed, setDataLoader] = useState(false);
    const history = useHistory();
    const [name, setName] = useState("");

    var reply_click = function()
    {
                history.push(`/medication-user/${this.id}`);
    }
    useEffect(() => {
        PharmacyService.getAll()
        .then(response => {
            setPharmacies(response.data);
            
                console.log(response.data);
              })
        .catch(e => {
            console.log(e);
        });
    }, []);

    // Using useEffect to call the API once mounted and set the data
    useEffect(() => {
        let id = window.location.pathname.split('/')[2];
        MedicationService.getByPharmacyId(id)
        .then(response => {
            setMedication(response.data);
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
        for (let i = 0; i<medication.length; i++) {

            let pharmacy_name = "";

            for(let p=0;p<pharmacies.length;p++)
            {
                if(pharmacies[p].id === Number(medication[i].pharmacyid))
                {

                    pharmacy_name = pharmacies[p].name;
                }
            }


            let myButtom = '<button class="button-pharmacy" id="'+medication[i].id+'"position="left" type="button">Vidi lek</button>';
            var myCol = $('<div class="row center-cols center-align id="row'+String(medication[i].id)+'""></div>');
            var myPanel = $('<div class="property-card" id="Card'+String(medication[i].id)+'"><div class="property-med-image"></div><div class="property-title"><span>'+String(medication[i].name)+'</span><p>Price: '+ String(medication[i].price)+' RSD</p><p>'+ String(pharmacy_name)+'</p>'+myButtom+'</div></div>');
            myPanel.appendTo(myCol);
            myCol.appendTo('#contentPanel');

            document.getElementById(String(medication[i].id)).onclick = reply_click;
        }
        
        
        
        
    };
    
    $(document).ready(function(){
        addCols(medication.length);
        return false;
    });



},[dataLodaed]);

function OnClick(e)
{
    for (let i = 0; i<medication.length; i++) {
        $("#Card"+String(medication[i].id)).show();
        $("#row"+String(medication[i].id)).show();
    }
    for (let i = 0; i<medication.length; i++) {
        if(String(medication[i].name)!=name && name!="")
        {
            $("#Card"+String(medication[i].id)).hide();
            $("#row"+String(medication[i].id)).hide();
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
                    <label id="label-search">Ime</label>
                    <input id="name"  type="text" class="input-search"  onChange={handleName}/>
                    <button id="button-search" type="button" onClick={OnClick}></button>
                </form>
                </div>
                <br />
                <div class="row" id="contentPanel">

                </div>
            </div>

        )
};