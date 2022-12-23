import React, { useMemo, useState, useEffect } from "react";
import {Card, ListGroup, ListGroupItem, Button, CardGroup, Container, Row} from 'react-bootstrap';
import { useHistory } from "react-router-dom";

export default function AdminSearchPage(){

    let history = useHistory();
    return(
        <Container>
            <Row>
                <Card>
                <Card.Header>PRETRAGA LEKOVA</Card.Header>
                <Card.Body>
                    <Card.Title>Pretrazite lekove</Card.Title>
                    <Button variant="primary" onClick={() => history.push('/admin/search/medication')}>Pretrazi</Button>
                </Card.Body>
                </Card>
                <Card>
                <Card.Header>PRETRAGA DERMATOLOGA</Card.Header>
                <Card.Body>
                    <Card.Title>Pretrazite dermatologe</Card.Title>
                    <Button variant="primary" onClick={() => history.push('/admin/search/dermatologists')}>Pretrazi</Button>
                </Card.Body>
                </Card>
                <Card>
                <Card.Header>PRETRAGA FARMACEUTA</Card.Header>
                <Card.Body>
                    <Card.Title>Pretrazite farmaceute</Card.Title>
                    <Button variant="primary" onClick={() => history.push('/admin/search/pharmacists')}>Pretrazi</Button>
                </Card.Body>
                </Card>
            </Row>
        </Container>
    );    
}