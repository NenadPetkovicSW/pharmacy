import React, { useMemo, useState, useEffect } from "react";
import {Card, ListGroup, ListGroupItem, Button, CardGroup, Container, Row} from 'react-bootstrap';
import { useHistory } from "react-router-dom";

export default function DermatologistsHome(){

    let history = useHistory();
    
    return(
        <Container>
            <Row>
                <Card>
                <Card.Header>Pregled svih dermatologa</Card.Header>
                <Card.Body>
                    <Card.Title>Pogledajte sve dermatologe</Card.Title>
                    <Card.Text>
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Sunt provident at iusto tempora eos architecto, voluptatum perferendis illum, placeat laboriosam aspernatur praesentium fugiat quaerat explicabo totam porro veritatis, molestias repellendus. 
                    </Card.Text>
                    <Button variant="primary" onClick={() => history.push('/dermatologists')}>Pregled</Button>
                </Card.Body>
                </Card>
            </Row>
            <Row>
            <Card>
                <Card.Header>Dodaj novog dermatologa</Card.Header>
                <Card.Body>
                    <Card.Title>Dodavanje</Card.Title>
                    <Card.Text>
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Sunt provident at iusto tempora eos architecto, voluptatum perferendis illum, placeat laboriosam aspernatur praesentium fugiat quaerat explicabo totam porro veritatis, molestias repellendus. 
                    </Card.Text>
                    <Button variant="primary" onClick={() => history.push('/dermatologists/add')}>Dodaj</Button>
                </Card.Body>
                </Card>
            </Row>
            <Row>
            <Card>
                <Card.Header>Pogledaj sve termine dermatologa</Card.Header>
                <Card.Body>
                    <Card.Title>Pregled</Card.Title>
                    <Card.Text>
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Sunt provident at iusto tempora eos architecto, voluptatum perferendis illum, placeat laboriosam aspernatur praesentium fugiat quaerat explicabo totam porro veritatis, molestias repellendus. 
                    </Card.Text>
                    <Button variant="primary" onClick={() => history.push('/dermatologists-appointments')}>Pregledaj</Button>
                </Card.Body>
                </Card>
            </Row>
        </Container>
    );    
}