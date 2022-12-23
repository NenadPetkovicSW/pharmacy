import React, { useMemo, useState, useEffect } from "react";
import {Card, ListGroup, ListGroupItem, Button, CardGroup, Container, Row} from 'react-bootstrap';
import { useHistory } from "react-router-dom";

export default function PatientSearchPage(){

    let history = useHistory();
    return(
        <Container>
            <Row>
                <Card>
                <Card.Header>PRETRAGA DERMATOLOGA</Card.Header>
                <Card.Body>
                    <Card.Title>Pretrazite dermatologe</Card.Title>
                    <Button variant="primary" onClick={() => history.push('/patient/search/dermatologists')}>Pretrazi</Button>
                </Card.Body>
                </Card>
                <Card>
                <Card.Header>PRETRAGA FARMACEUTA</Card.Header>
                <Card.Body>
                    <Card.Title>Pretrazite farmaceuta</Card.Title>
                    <Button variant="primary" onClick={() => history.push('/patient/search/pharmacists')}>Pretrazi</Button>
                </Card.Body>
                </Card>
            </Row>
        </Container>
    );    
}