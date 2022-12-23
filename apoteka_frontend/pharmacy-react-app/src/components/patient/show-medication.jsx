import {useEffect, useState} from "react";
import { Button, Card, Container, Form, Row} from "react-bootstrap";
import { useHistory } from "react-router-dom";
import medicationService from '../common/medications-axios/medications-service';
import medicationOrderService from '../common/medications-axios/medication-user-order';
import allergiesService from '../common/allergies-axios/allergies-service';
import DatePicker from "react-datepicker";
import "react-confirm-alert/src/react-confirm-alert.css";
import "../css/slider.css"
import { confirmAlert } from 'react-confirm-alert'; 
export default function ShowMedication(){

    const[data, setData] = useState([]);
    const[allergies, setAllergies] = useState([]);
    const[amount, setAmount] = useState(1);
    var datNow = new Date(Date.now());
    const [date, setDate] = useState(new Date(datNow.getFullYear(),datNow.getMonth(),datNow.getDay(),0,0,0,0));
    const history = useHistory();

    

    useEffect(() => {
        let id = window.location.pathname.split('/')[2];
        medicationService.get(id)
        .then(response => {
                setData(response.data);
                
                console.log(response.data);
              })
        .catch(e => {
            console.log(e);
        });
    }, []);

    useEffect(() => {
        let id = window.location.pathname.split('/')[2];
        allergiesService.getMedicationAllergies(id)
        .then(response => {
                setAllergies(response.data);
                console.log(response.data);
              })
        .catch(e => {
            console.log(e);
        });
    }, []);


    const validateOrder = () => {
        if(data.amount > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    const returnAllergies = () => {
        let allergies_string = "";
        
        for(let a=0;a<allergies.length; a++)
        {
            allergies_string = allergies_string + " " +String(allergies[a].name); 
        }

        return allergies_string;
        
    }

    const naruci = () =>{
        let pomocna = JSON.parse(localStorage.getItem('LogedUser'));
        let cena = Number(data.price * amount);
        var narudzbenica = {
            id : null,
            amount : amount,
            pharmacyId : Number(data.pharmacyid),
            medicationId : data.id,
            reservationDate : new Date(Date.now()),
            deliveryDate : date,
            amount : amount,
            price : cena,
            patientId : pomocna.id
        }

        
        let pom_nar = medicationOrderService.create(narudzbenica);
        if(pom_nar != null)
        {
            alertOrder();
            window.location.reload();
        }
        else
        {
            alert("Greska na serveru!");
        }
        }

        const alertOrder = () => {
            confirmAlert({
                customUI: ({ onClose }) => {
                  return (
                    <div className='custom-ui'>
                        <Form>
                            <Form.Group controlId="formBasicEmail">
                                <Form.Label>Uspešno naručeno!</Form.Label>
                            </Form.Group>
                            <Button variant="outline-light" onClick={()=>{window.location.reload()}}>Nazad</Button>{'  '}
                            
                        </Form>
                    </div>
                  );
                }
              });
        };

    

    return(
        <Container>
            <Row sm={12}>
                <Card sm={8}>
                    <Card.Header>{data.name}</Card.Header>
                    <Card.Body>
                        
                        <Card.Text>
                        Cena:
                        </Card.Text>{}
                        <Card.Text><h5>
                        {data.price} RSD</h5>
                        </Card.Text>{}
                        <Card.Text>
                        Ispod se nalaze alergeni leka:
                        </Card.Text>{}
                        <Card.Text>
                        {returnAllergies()}
                        </Card.Text>{}
                        
                        <div class="slidecontainer">
                        <input type="range"  min="1" max={data.amount} value={amount} id="myRange" onChange={(e) => {setAmount(e.target.value)}} />
                        </ div>
                        <Form.Text>Kolicina: <h5>{amount}</h5></Form.Text>
                        <Form.Text>Datum preuzimanja:</Form.Text>
                        <DatePicker selected={date} onChange={date1 => setDate(date1)} /><br />
                        <Button disabled={!validateOrder()} variant="primary "onClick={naruci} >Naruci lek</Button>{' '}
                        <p id="alertP" hidden={validateOrder()}>Nema na stanju!</p>
                    </Card.Body>
                </Card>
                <Card sm={4} style={{width: 500, legth: 100}}>
                    <Card.Img variant="top" src="https://images.unsplash.com/photo-1616526628254-3237a81c18ce?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80" />
                </Card>
            </Row>
        </Container>

    );
}