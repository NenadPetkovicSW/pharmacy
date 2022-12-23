import {useEffect, useState} from "react";
import { Button, Card, Container, Form, Row } from "react-bootstrap";
import { useHistory } from "react-router-dom";
import medicationService from '../common/medications-axios/medications-service';
import { confirmAlert } from 'react-confirm-alert'; 
import "react-confirm-alert/src/react-confirm-alert.css";



export default function ShowMedication(){

    const[data, setData] = useState([]);
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

    const deleteMedication = () => {
        confirmAlert({
            customUI: ({ onClose }) => {
              return (
                <div className='custom-ui'>
                  <h1>Da li ste sigurni?</h1>
                  <p>Da li zelite da obrisete ovaj lek?</p>
                  <Button variant="outline-light" onClick={onClose}>Ne</Button>{'  '}
                  <Button variant="outline-light"
                    onClick={() => {
                        let id = window.location.pathname.split('/')[2];
                        medicationService.delete(id)
                        .then(response => {
                            onClose();
                                history.push(`/medications/`);
                                console.log(response.data);
                              })
                        .catch(e => {
                            console.log(e);
                        });
                      
                    }}
                  >
                    Da, obrisi!
                  </Button>
                </div>
              );
            }
          });
    };

    const updateMedicationPrice = () => {
        confirmAlert({
            customUI: ({ onClose }) => {
              return (
                <div className='custom-ui'>
                    <Form>
                        <Form.Group controlId="formBasicEmail">
                            <Form.Label>Promena cene leka</Form.Label>
                            <Form.Control type="price" placeholder="Unesite cenu" onChange={(e) => {data.price = e.target.value}}/>
                        </Form.Group>
                        <Button variant="outline-light" onClick={onClose}>Nazad</Button>{'  '}
                        <Button variant="outline-light"
                            onClick={() => {
                                let id = window.location.pathname.split('/')[2];
                                medicationService.update(id, data)
                                .then(response => {
                                    onClose();
                                        history.push(`/medications/`);
                                        console.log(response.data);
                                    })
                                .catch(e => {
                                    console.log(e);
                                });
                            
                            }}
                        >
                            Sacuvaj
                        </Button>
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
                    <Card.Header>Lek</Card.Header>
                    <Card.Body>
                        <Card.Title>{data.id}</Card.Title>
                        <Card.Text>
                        Ispod se nalaze alergeni leka:
                        </Card.Text>
                        <Button variant="primary"onClick={() => updateMedicationPrice()} >Promena cene</Button>{' '}
                        <Button variant="primary" onClick={() => deleteMedication()}>Brisanje leka</Button>
                    </Card.Body>
                </Card>
                <Card sm={4} style={{width: 500, legth: 100}}>
                    <Card.Img variant="top" src="https://images.unsplash.com/photo-1616526628254-3237a81c18ce?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80" />
                </Card>
            </Row>
        </Container>

    );
}