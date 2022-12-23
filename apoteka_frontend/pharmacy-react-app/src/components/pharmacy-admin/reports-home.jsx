import React, { useMemo, useState, useEffect } from "react";
import {Card, ListGroup, ListGroupItem, Button, CardGroup, Container, Row} from 'react-bootstrap';
import { useHistory } from "react-router-dom";

export default function ReportsHome(){

    let history = useHistory();
    return(
        <Container>
            <Row>
                <Card style={{width: '800px'}}>
                <Card.Header>Izvestaji svih pregleda</Card.Header>
                <Card.Body>
                    <Card.Title>Pregledi</Card.Title>
                    <Card.Text>
                        Ovde mozete da vidite izvestaje pregleda za ovu apoteku. Mozete  videti preglede za mesec, kvartal u godini ili podatke po godinama.
                    </Card.Text>
                    <Button variant="primary" onClick={() => history.push('/reports/monthly')}>Pregled Mesecni</Button>
                    <Button variant="primary" onClick={() => history.push('/reports/quarterly')}>Pregled Kvartalni</Button>
                    <Button variant="primary" onClick={() => history.push('/reports/yearly')}>Pregled Godisnji</Button>
                </Card.Body>
                </Card>
            </Row>
            <Row>
                <Card style={{width: '800px'}}>
                    <Card.Header>Izvestaji potrosnje lekova</Card.Header>
                    <Card.Body>
                        <Card.Title>Potrosnja</Card.Title>
                        <Card.Text>
                            Ovde mozete da vidite izvestaje potrosnje lekova za ovu apoteku. Mozete videti potrosnju za mesec, kvartal u godini ili podatke po godinama.
                        </Card.Text>
                        <Button variant="primary" onClick={() => history.push('/reports/medication/monthly')}>Pregled Mesecni</Button>
                        <Button variant="primary" onClick={() => history.push('/reports/medication/quarterly')}>Pregled Kvartalni</Button>
                        <Button variant="primary" onClick={() => history.push('/reports/medication/yearly')}>Pregled Godisnji</Button>
                    </Card.Body>
                </Card>
            </Row>
        </Container>
    );    
}