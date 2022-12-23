import React, { useMemo, useState, useEffect } from "react";
import {Card, ListGroup, ListGroupItem, Button, CardGroup, Container, Row} from 'react-bootstrap';
import { useHistory } from "react-router-dom";

export default function PharmacyHome(){

    let history = useHistory();
    return(
        <Container>
            <Row>
                <Card>
                <Card.Header>Pregled svih apoteka</Card.Header>
                <Card.Body>
                    <Card.Title>Pogledajte sve apoteke</Card.Title>
                    <Card.Text>
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Sunt provident at iusto tempora eos architecto, voluptatum perferendis illum, placeat laboriosam aspernatur praesentium fugiat quaerat explicabo totam porro veritatis, molestias repellendus. 
                    </Card.Text>
                    <Button variant="primary" onClick={() => history.push('/pharmacies')}>Pregled apoteka</Button>
                </Card.Body>
                </Card>
            </Row>
            <Row>
            <Card>
                <Card.Header>Dodaj novu apoteku</Card.Header>
                <Card.Body>
                    <Card.Title>Dodavanje</Card.Title>
                    <Card.Text>
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Sunt provident at iusto tempora eos architecto, voluptatum perferendis illum, placeat laboriosam aspernatur praesentium fugiat quaerat explicabo totam porro veritatis, molestias repellendus. 
                    </Card.Text>
                    <Button variant="primary" onClick={() => history.push('/pharmacies/add')}>Dodaj apoteku</Button>
                </Card.Body>
                </Card>
            </Row>
            <Row>
            <Card>
                <Card.Header>Pregled svih lekova</Card.Header>
                <Card.Body>
                    <Card.Title>Lekovi</Card.Title>
                    <Card.Text>
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Sunt provident at iusto tempora eos architecto, voluptatum perferendis illum, placeat laboriosam aspernatur praesentium fugiat quaerat explicabo totam porro veritatis, molestias repellendus. 
                    </Card.Text>
                    <Button variant="primary" onClick={() => history.push('/medications')}>Pregled lekova</Button>
                </Card.Body>
                </Card>
            </Row>
        </Container>
    );    
}