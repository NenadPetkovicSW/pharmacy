import {useEffect, useState} from "react";
import { Button, Card, Container, Row } from "react-bootstrap";
import dermatologistsService from '../common/dermatologists-axios/dermatologists-service';
import { confirmAlert } from 'react-confirm-alert'; 
import { useHistory } from "react-router-dom";
import "react-confirm-alert/src/react-confirm-alert.css";

export default function ShowDermatologist(){

    const[data, setData] = useState([]);
    const history = useHistory();

    useEffect(() => {
        let id = window.location.pathname.split('/')[2];
        dermatologistsService.get(id)
        .then(response => {
                setData(response.data);
                console.log(response.data);
              })
        .catch(e => {
            console.log(e);
        });
    }, []);

    const addAppoint = () => {
      history.push(`/admin-add-appointment-dermatologist/${data.id}`);
  };

    const addWorkTime = () => {
      history.push(`/admin-add-worktime-dermatologist/${data.id}`);
    }

    const deleteDermatologist = () => {
        confirmAlert({
            customUI: ({ onClose }) => {
              return (
                <div className='custom-ui'>
                  <h1>Da li ste sigurni?</h1>
                  <p>Da li zelite da obrisete ovog dermatologa?</p>
                  <Button variant="outline-light" onClick={onClose}>Ne</Button>{'  '}
                  <Button variant="outline-light"
                    onClick={() => {
                        let id = window.location.pathname.split('/')[2];
                        dermatologistsService.delete(id)
                        .then(response => {
                            onClose();
                                history.push(`/dermatologists/`);
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

    return(
        <Container>
            <Row sm={12}>
                <Card sm={8}>
                    <Card.Header>Dermatolog</Card.Header>
                    <Card.Body>
                        <Card.Title>{data.firstName}</Card.Title>
                        <Card.Text>
                        Ako vam je potreban pregled kliknite dugme ispod.
                        </Card.Text>
                        <Button variant="primary" onClick={() => addWorkTime()}>Dodavanje radnog vremena</Button>{' '}
                        <Button variant="primary" onClick={() => addAppoint()}>Zakazivanje termina</Button>{' '}
                        <Button variant="primary" onClick={() => deleteDermatologist()}>Brisanje dermatologa</Button>
                    </Card.Body>
                </Card>
                <Card sm={4} style={{width: 500, legth: 100}}>
                    <Card.Img variant="top" src="https://images.unsplash.com/photo-1559839734-2b71ea197ec2?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80" />
                </Card>
            </Row>
        </Container>

    );
}