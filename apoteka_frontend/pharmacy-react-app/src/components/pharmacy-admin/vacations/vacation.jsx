import { Fragment } from "react";
import {useEffect, useState} from "react";
import { Button, Card, Container, Row , Form} from "react-bootstrap";
import { useHistory } from "react-router-dom";
import vacationService from '../../common/vacation-paid-leave-axios/vacation-service';

export default function ShowVacation(){

    const[data, setData] = useState([]);
    const history = useHistory();
    const [denialClick, setDenialClick] = useState(false);

    useEffect(() => {
        let id = window.location.pathname.split('/')[2];
        vacationService.get(id)
        .then(response => {
                setData(response.data);
                console.log(response.data);
              })
        .catch(e => {
            console.log(e);
        });
    }, []);


    const approveRequest = () => {
        setDenialClick(false);
        setData({...data, approved: true});
    
        let id = window.location.pathname.split('/')[2];
        vacationService.update(id, data, data.approved, data.response)
        .then(response => {
                setData(response.data);
                console.log(response.data);
              })
        .catch(e => {
            console.log(e);
        });
    }

    const denialForm = () => {
        setDenialClick(true);
    }

    const denyRequest = () => {
        setData({...data, approved: false});
        let id = window.location.pathname.split('/')[2];
        vacationService.update(id, data, data.approved, data.response)
        .then(response => {
                setData(response.data);
                console.log(response.data);
              })
        .catch(e => {
            console.log(e);
        });
    }

    const handleTextChange = (e) => {
        setData({...data,response: e.target.value});
    }

    

    return(
        <Container>
            <Row sm={12}>
                <Card sm={8}>
                    <Card.Header>{data.pharmacist? 'Farmaceut' : 'Dermatolog'}</Card.Header>
                    <Card.Body>
                        <Card.Title>{data.employeeId}</Card.Title>
                        <Card.Text>
                        Reasoning: {data.reasoning}
                        </Card.Text>
                        <Button variant="primary" onClick={() => approveRequest()}>Dozvoli</Button>{' '}
                        <Button variant="primary" onClick={() => denialForm()}>Odbij</Button>
                    </Card.Body>
                </Card>
                <Card sm={4} style={{width: 500, legth: 100}}>
                    <Card.Img variant="top" src="https://images.unsplash.com/photo-1580281658223-9b93f18ae9ae?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80" />
                </Card>
            </Row>
            {denialClick ? (            
                <Row sm={12}>
                    <Card sm={8} style={{width: 690, legth: 100}}>
                        <Card.Header>Forma za odbijanje</Card.Header>
                        <Card.Body>
                            <Form.Group controlId="exampleForm.ControlTextarea1">
                            <Form.Label>Odgovor</Form.Label>
                            <Form.Control as="textarea" rows={3} onChange={(e) => handleTextChange(e)}/>
                            </Form.Group>
                            <Button variant="primary" onClick={() => denyRequest()}>Posalji</Button>
                        </Card.Body>
                    </Card>

                </Row>      
            ):''}
        </Container>

    );
}