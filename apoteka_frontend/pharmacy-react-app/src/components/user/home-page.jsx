import React, { useMemo, useState, useEffect } from "react";
import {Card, ListGroup, ListGroupItem, Button, CardGroup, Container, Row} from 'react-bootstrap';
import { useHistory } from "react-router-dom";


export default function HomePage(){
    let history = useHistory();
    return(
        <Container>
            <Row>
                <Card>
                <Card.Header>Pregled svih korisnika</Card.Header>
                <Card.Body>
                    <Card.Title>Pogledajte sve korisnike</Card.Title>
                    <Button variant="primary" onClick={() => history.push('/profile')}>Pregled</Button>
                </Card.Body>
                </Card>

                <Card>
                <Card.Header>Pregled apoteka</Card.Header>
                <Card.Body>
                    <Card.Title>Pregleadajte sve apoteke</Card.Title>
                    <Button variant="primary" onClick={() => history.push('/pharmacies')}>Pregled</Button>
                </Card.Body>
                </Card>

                <Card>
                <Card.Header>Dodavanje novog korisnika</Card.Header>
                <Card.Body>
                    <Card.Title>Dodavanje korisnika</Card.Title>
                    <Button variant="primary" onClick={() => history.push('/user/add')}>Dodavanje</Button>
                </Card.Body>
                </Card>

                <Card>
                <Card.Header>Pregled alergija</Card.Header>
                <Card.Body>
                    <Card.Title>Pregledajte alergije</Card.Title>
                    <Button variant="primary" onClick={() => history.push('/allergies')}>Pregled</Button>
                </Card.Body>
                </Card>

                <Card>
                <Card.Header>Dodaj alergije</Card.Header>
                <Card.Body>
                    <Card.Title>Dodavanje alergije</Card.Title>
                    <Button variant="primary" onClick={() => history.push('/allergies-add')}>Dodaj</Button>
                </Card.Body>
                </Card>


            </Row>

        </Container>
        
    );    
}