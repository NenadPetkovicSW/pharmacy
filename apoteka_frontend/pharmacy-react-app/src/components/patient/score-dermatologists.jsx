import dermatologistsService from "../common/dermatologists-axios/dermatologists-service";
import React, { useMemo, useState, useEffect } from "react";
import scoreService from "../common/dermatologists-axios/dermatologists-score-service";
import $ from 'jquery';
import '../css/ItemCard.css'
import { useHistory } from "react-router-dom";
import { confirmAlert } from 'react-confirm-alert'; 
import { Button, Card, Container, Form, Row} from "react-bootstrap";
export default function ScoreDermatologist(){
    const [dermatologists, setDermatologists] = useState([]);
    const [dermatologistsScore, setDermatologistsScore] = useState([]);
    const history = useHistory();
    const [name, setName] = useState("");
    const [lastName, setLastName] = useState("");
    const [pharmacyId, setPharmacyId]=useState("");
    const [rating, setRating]=useState(1);
    const [allScores, setAllScores] = useState([]);
    const [canMont, setCanMont] = useState(false);
    const [canMont1, setCanMont1] = useState(false);

    // Using useEffect to call the API once mounted and set the data
    useEffect(() => {
        let pomocna = JSON.parse(localStorage.getItem('LogedUser'));
        dermatologistsService.getScorable(pomocna.id)
        .then(response => {
            setDermatologists(response.data);
                console.log(response.data);
                setCanMont1(!canMont1);
            })
        .catch(e => {
            console.log(e);
        });
    },[]);

    useEffect(() => {
        scoreService.getAll()
        .then(response => {
            setAllScores(response.data);
    
                console.log(response.data);
                setCanMont(!canMont);
            })
        .catch(e => {
            console.log(e);
        });
    },[canMont1]);

    const alertOrder = () => {
        confirmAlert({
            customUI: ({ onClose }) => {
              return (
                <div className='custom-ui'>
                    <Form>
                        <Form.Group controlId="formBasicEmail">
                            <Form.Label>Uspešno ocenjeno!</Form.Label>
                        </Form.Group>
                        <Button variant="outline-light" onClick={()=>{window.location.reload()}}>Nazad</Button>{'  '}
                        
                    </Form>
                </div>
              );
            }
          });
    };

    var reply_click = function()
    {
        

        let pom_score = {
            id : null,
            idDermatologist : 0,
            idUser : 0,
            score : 1
        };

        let pom_dermatologistsId = this.id; 

        pom_score.idDermatologist = Number(pom_dermatologistsId);
        let pomocna_user = JSON.parse(localStorage.getItem('LogedUser'));
        pom_score.idUser = Number(pomocna_user.id); 
        pom_score.score = Number(document.getElementById("rating"+this.id).value);

        if(pom_score.score < 1)
        {
            pom_score = 1;
        }
        if(pom_score.score > 5)
        {
            pom_score = 5;
        }

        scoreService.create(pom_score)
        .then(response => {
                console.log(response.data);
                alertOrder();
                window.location.reload();
            })
        .catch(e => {
            console.log(e);
        });
        
        
    }

    useEffect(() => {
    document.getElementById("contentPanel").innerHTML = "";
    var addCols = function (num){
        
        let pomocna_moja = JSON.parse(localStorage.getItem('LogedUser'));
        for (let i = 0; i<dermatologists.length; i++) {
            var scoreToShow = 0;

            for(let j = 0; j<allScores.length;j++)
            {
                if(allScores[j].idUser == pomocna_moja.id && allScores[j].idDermatologist ==  dermatologists[i].id)
                {
                    scoreToShow = allScores[j].score;
                }
            }
            
            var myCol = $('<div class="row center-cols center-align id="row'+String(dermatologists[i].id)+'""></div>');
            let myRating = '<input type="number" value="'+scoreToShow+'" id="rating'+ String(dermatologists[i].id) +'" placeholder="your rating" step="0.5" min="1" max="5">';
            let myButtom = '<button class="button-pharmacy" id="'+dermatologists[i].id+'"position="left" type="button">Oceni</button>';
            var myPanel = $('<div class="property-card" id="Card'+String(dermatologists[i].id)+'"><div class="property-image-pharmacist"></div><div class="property-title"><span>'+String(dermatologists[i].firstName + " " + dermatologists[i].lastName)+'</span><p>Score:'+scoreToShow+'</p>'+ myRating +'<br />'+myButtom+'</div></div>');
            myPanel.appendTo(myCol);
            myCol.appendTo('#contentPanel');
            
            
            
            document.getElementById(String(dermatologists[i].id)).onclick = reply_click;
        }   
    };
    
    $(document).ready(function(){
        addCols(dermatologists.length);
        return false;
    });
    },[canMont]);


      
    return (
        <div class="container">
            
            <br />
            <div class="row" id="contentPanel">

            </div>
        </div>

    )
};