import allergiesServise from "../common/allergies-axios/allergies-service";
import userService from "../common/user-axios/user-service";
import React, { useMemo, useState, useEffect } from "react";
import $ from 'jquery';
import '../css/ItemCard.css'
import { useHistory } from "react-router-dom";
import { confirmAlert } from 'react-confirm-alert'; 
import { Button, Card, Container, Form, Row} from "react-bootstrap";
export default function ScoreDermatologist(){
    const [allergies, setAllergies] = useState([]);
    const history = useHistory();
    const [canMont1, setCanMont1] = useState(false);
    
    // Using useEffect to call the API once mounted and set the data
    useEffect(() => {

        allergiesServise.getAll()
        .then(response => {
                setAllergies(response.data);
                console.log(response.data);
                setCanMont1(!canMont1);
            })
        .catch(e => {
            console.log(e);
        });
    },[]);

    
    const on_click = () => {
        let pomocna = JSON.parse(localStorage.getItem('LogedUser'));
        let alergije_upis = "";
        for (let i = 0; i<allergies.length; i++) {
        if(document.getElementById("al"+allergies[i].id).checked)
        {
            if(alergije_upis!="")
            {
                alergije_upis = alergije_upis + ",";
            }
            alergije_upis = alergije_upis + document.getElementById("al"+allergies[i].id).value;
            
        }
        
        
        }
        pomocna.alergiesIds = alergije_upis;
        localStorage.setItem('LogedUser', JSON.stringify(pomocna));

        userService.update(pomocna.id,pomocna)
        .then(response => {   
            
            console.log(response.data);
          })
        .catch(e => {
                console.log(e);
        });

        alertOrder();
    };



    const alertOrder = () => {
        confirmAlert({
            customUI: ({ onClose }) => {
              return (
                <div className='custom-ui'>
                    <Form>
                        <Form.Group controlId="formBasicEmail">
                            <Form.Label>Uspešno izmenjeno!</Form.Label>
                        </Form.Group>
                        
                        
                    </Form>
                </div>
              );
            }
          });
    };


    useEffect(() => {
    document.getElementById("contentPanel").innerHTML = "";
    var addCols = function (num){
        let pomocna = JSON.parse(localStorage.getItem('LogedUser'));
        var userAllergie = (String(pomocna.alergiesIds).replace(" ", "").split(","));
        
        for (let i = 0; i<allergies.length; i++) {
            

            var myCol = $('<div class="row center-cols center-align id="row'+String(allergies[i].id)+'""></div>');
            var myPanel = $('<div class="property-card-allergies" id="Card'+String(allergies[i].id)+'"><label  for="vehicle'+String(allergies[i].id)+'">'+String(allergies[i].name)+'</label><br><div style="margin-right:auto; margin-left:auto;"><input align="center" type="checkbox" id="al'+String(allergies[i].id)+'"  name="'+String(allergies[i].name)+'" value="'+String(allergies[i].id)+'"></div></div>');
            myPanel.appendTo(myCol);
            myCol.appendTo('#contentPanel');

        }   
        
    };
    
    $(document).ready(function(){
        addCols(allergies.length);
        
        return false;
    });
    },[canMont1]);


      
    return (
        <div class="container">
            <button class="button-allergie" onClick={on_click}>Završi izmene</button><br />
            <br />
            <div class="row" id="contentPanel">

            </div>
        </div>

    )
};