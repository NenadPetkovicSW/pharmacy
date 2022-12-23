import React, { useMemo, useState, useEffect } from "react";
import {Card, ListGroup, ListGroupItem, Button, CardGroup, Container, Row} from 'react-bootstrap';
import { useHistory } from "react-router-dom";

export default function MyHome(){

    let history = useHistory();
    return(
        <Container>
            
                <Card >
                <Card.Header>Lista apoteka u sistemu</Card.Header>
                <Card.Body>
                    <Card.Title>Pogledajte sve apoteke</Card.Title>
                    
                    <Button variant="primary" onClick={() => history.push('/MyPhaSearch')}>Pregled apoteka</Button>
                </Card.Body>
                </Card>
                <Card>
                <Card.Header>Lista slobodnih apoteka</Card.Header>
                <Card.Body>
                    <Card.Title>Pogledajte apoteke sa slobodnim farmaceutima</Card.Title>
                    
                    <Button variant="primary" onClick={() => history.push('/pharmacist-appointments-search')}>Pogledaj</Button>
                </Card.Body>
                </Card>
                <Card>
                <Card.Header>Moj profil</Card.Header>
                <Card.Body>
                    <Card.Title>Pogledajte svoj profil</Card.Title>
                    
                    <Button variant="primary" onClick={() => history.push('/MyProfile')}>Pregled profila</Button>
                </Card.Body>
                </Card>
                <Card>
                <Card.Header>Oceni dermatologe</Card.Header>
                <Card.Body>
                    <Card.Title>Ocenite dermatologe</Card.Title>
                    
                    <Button variant="primary" onClick={() => history.push('/scoreDermatologist')}>Oceni</Button>
                </Card.Body>
                </Card>
                <Card>
                <Card.Header>Oceni farmaceute</Card.Header>
                <Card.Body>
                    <Card.Title>Ocenite farmaceute</Card.Title>
                    
                    <Button variant="primary" onClick={() => history.push('/scorePharmacist')}>Oceni</Button>
                </Card.Body>
                </Card>
                <Card>
                <Card.Header>Oceni lekove</Card.Header>
                <Card.Body>
                    <Card.Title>Ocenite lekove</Card.Title>
                    
                    <Button variant="primary" onClick={() => history.push('/scoreMedication')}>Oceni</Button>
                </Card.Body>
                </Card>
                <Card>
                <Card.Header>Oceni apoteke</Card.Header>
                <Card.Body>
                    <Card.Title>Ocenite apoteke</Card.Title>
                    
                    <Button variant="primary" onClick={() => history.push('/scorePharmacy')}>Oceni</Button>
                </Card.Body>
                </Card>
                <Card>
                <Card.Header>Pretrage</Card.Header>
                <Card.Body>
                    <Card.Title>Pretrazite po sajtu</Card.Title>
                    
                    <Button variant="primary" onClick={() => history.push('/patient/search')}>Pregled</Button>
                </Card.Body>
                </Card>

                <Card>
                <Card.Header>Moji zakzani termini kod dermatologa</Card.Header>
                <Card.Body>
                    <Card.Title>Otkažite svoje termine kod dermatologa</Card.Title>
                    
                    <Button variant="primary" onClick={() => history.push('/dermatologists-appointments-cancel')}>Termini</Button>
                </Card.Body>
                </Card>
                <Card>
                <Card.Header>Moji zakzani termini kod farmaceuta</Card.Header>
                <Card.Body>
                    <Card.Title>Otkažite svoje termine kod farmaceuta</Card.Title>
                    
                    <Button variant="primary" onClick={() => history.push('/pharmacist-appointments-cancel')}>Termini</Button>
                </Card.Body>
                </Card>
                <Card>
                <Card.Header>Moje rezervacije lekova</Card.Header>
                <Card.Body>
                    <Card.Title>Otkažite svoje rezervacije lekova</Card.Title>
                    
                    <Button variant="primary" onClick={() => history.push('/cancel-user-order/')}>Termini</Button>
                </Card.Body>
                </Card>
                
            
            
        </Container>
    );    
}