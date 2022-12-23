import React, { useMemo, useState, useEffect } from "react";
import {Card, ListGroup, ListGroupItem, Button, CardGroup, Container, Row} from 'react-bootstrap';
import { useHistory } from "react-router-dom";

export default function PharmacistsHome(){

    let history = useHistory();
    return(
        <Container>
            <Row>
                <Card>
                <Card.Header>Pregled svih farmaceuta</Card.Header>
                <Card.Body>
                    <Card.Title>Pogledajte sve farmaceute</Card.Title>
                    <Card.Text>
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Sunt provident at iusto tempora eos architecto, voluptatum perferendis illum, placeat laboriosam aspernatur praesentium fugiat quaerat explicabo totam porro veritatis, molestias repellendus. 
                    </Card.Text>
                    <Button variant="primary" onClick={() => history.push('/pharmacists')}>Pregled</Button>
                </Card.Body>
                </Card>
            </Row>
            <Row>
            <Card>
                <Card.Header>Dodaj novog farmaceuta</Card.Header>
                <Card.Body>
                    <Card.Title>Dodavanje</Card.Title>
                    <Card.Text>
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Sunt provident at iusto tempora eos architecto, voluptatum perferendis illum, placeat laboriosam aspernatur praesentium fugiat quaerat explicabo totam porro veritatis, molestias repellendus. 
                    </Card.Text>
                    <Button variant="primary" onClick={() => history.push('/pharmacists/add')}>Dodaj</Button>
                </Card.Body>
                </Card>
            </Row>
        </Container>
    );    
}