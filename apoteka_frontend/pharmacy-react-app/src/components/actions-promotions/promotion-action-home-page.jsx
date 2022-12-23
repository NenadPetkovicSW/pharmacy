import React, { useMemo, useState, useEffect } from "react";
import {Card, ListGroup, ListGroupItem, Button, CardGroup, Container, Row} from 'react-bootstrap';
import { useHistory } from "react-router-dom";

export default function PromotionActionHome(){

    let history = useHistory();
    return(
        <Container>
            <Row>
                <Card>
                <Card.Header>Promocije</Card.Header>
                <Card.Body>
                    <Card.Title>Dodaj</Card.Title>
                    <Card.Text>
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Sunt provident at iusto tempora eos architecto, voluptatum perferendis illum, placeat laboriosam aspernatur praesentium fugiat quaerat explicabo totam porro veritatis, molestias repellendus. 
                    </Card.Text>
                    <Button variant="primary" onClick={() => history.push('/add-promotion')}>Dodaj promociju</Button>
                </Card.Body>
                </Card>
            </Row>
            <Row>
                <Card>
                <Card.Header>Akcije</Card.Header>
                <Card.Body>
                    <Card.Title>Dodaj</Card.Title>
                    <Card.Text>
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Sunt provident at iusto tempora eos architecto, voluptatum perferendis illum, placeat laboriosam aspernatur praesentium fugiat quaerat explicabo totam porro veritatis, molestias repellendus. 
                    </Card.Text>
                    <Button variant="primary" onClick={() => history.push('/add-action')}>Dodaj akciju</Button>
                </Card.Body>
                </Card>
            </Row>
        </Container>
    );    
}