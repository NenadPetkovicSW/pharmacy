import {Card, ListGroup, ListGroupItem, Button, CardGroup, ButtonGroup} from 'react-bootstrap';
import {useEffect, useState} from "react";
import pharmaciesService from '../common/pharmacies-axios/pharmacies-service';

import { useHistory } from "react-router-dom";

export default function ShowPharmacy(props){

    const[data, setData] = useState([]);
    const history = useHistory();

    useEffect(() => {
        let id = window.location.pathname.split('/')[2];
        pharmaciesService.get(id)
        .then(response => {
                setData(response.data);
                console.log(response.data);
              })
        .catch(e => {
            console.log(e);
        });
    }, []);

    const showMedications = () => {
        history.push(`/medications/pharmacy/${data.id}`);
    };

    const addMedication = () => {
        history.push(`/medications/add`);
    };

    return(
        <CardGroup>
            <Card style={{ width: '65rem'}}>
                <Card.Img variant="top" src="https://images.unsplash.com/photo-1586015555751-63bb77f4322a?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80" />
                <Card.Body>
                    <Card.Title>{data.name}</Card.Title>
                    <Card.Text>
                    Apoteka
                    </Card.Text>
                </Card.Body>
                <ListGroup className="list-group-flush">
                    <ListGroupItem>Address: {data.address}</ListGroupItem>
                    <ListGroupItem>Average score: {data.averageScore}</ListGroupItem>
                </ListGroup>
                <Card.Body>
                    <Button variant="primary" size="lg" block>Make an appointment</Button>{' '}
                    <Button onClick={showMedications} variant="primary" size="lg" block>See avaliable medications</Button>{' '}
                    <Button onClick={addMedication} variant="primary" size="lg" block>Add medication</Button>{' '}
                </Card.Body>

            </Card>
        </CardGroup>


    );
}