import {Card, ListGroup, ListGroupItem, Button, CardGroup, ButtonGroup} from 'react-bootstrap';
import {Fragment, useEffect, useState} from "react";
import pharmaciesService from '../common/pharmacies-axios/pharmacies-service';
import { useHistory } from "react-router-dom";
import Map from './map';
import 'leaflet/dist/leaflet.css';
import Geolocation from './geolocation';

import './stars.css';



function StarRating({count, value, 
  inactiveColor='#ddd',
  size=24,
  activeColor='#f00', onChange}) {

    // short trick 
    const stars = Array.from({length: count}, () => '★')

    // Internal handle change function
    const handleChange = (value) => {
      
    }

    return (
      <div>
        {stars.map((s, index) => {
          let style = inactiveColor;
          if (index < value) {
            style=activeColor;
          }
          return (
            <span className={"star"}  
              key={index}
              style={{color: style, width:size, height:size, fontSize: size}}
              onClick={()=>handleChange(index)}>{s}</span>
          )
        })}
        {value}
      </div>
    )
}


export default function ShowPharmacy(props){
    const[data, setData] = useState([]);
    const history = useHistory();
    const[actions, setActions] = useState(null);
    const[promotions, setPromotions] = useState(null);
    const [rating, setRating] = useState(3);

    //ovde ce imati opciju da vidi akcije/promocije
    useEffect(() => {
        let id = window.location.pathname.split('/')[2];
        pharmaciesService.get(id)
        .then(response => {
                setData(response.data);
                setRating(response.data.averageScore);
                console.log(response.data);
              })
        .catch(e => {
            console.log(e);
        });
        pharmaciesService.getAllActions(id)
        .then(response => {
                setActions(response.data);
                console.log(response.data);
              })
        .catch(e => {
            console.log(e);
        });
        pharmaciesService.getAllPromotions(id)
        .then(response => {
                setPromotions(response.data);
                console.log(response.data);
              })
        .catch(e => {
            console.log(e);
        });
    }, []);

    const showActions = () => {
        history.push(`/actions/pharmacy/${data.id}`);
    };

    const showPromotions = () => {
        history.push(`/promotions/pharmacy/${data.id}`);
    };

    const showMedications = () => {
        history.push(`/medications-in-pharmacy/${data.id}`);
    };

    const showDerAppointments = () => {
        history.push(`/dermatologists-appointments/${data.id}`);
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
                    <ListGroupItem>Adresa: {data.address}</ListGroupItem>
                    <ListGroupItem>
                        <StarRating 
                          count={5}
                          size={40}
                          value={rating}
                          activeColor ={'red'}
                          inactiveColor={'#ddd'}
                            /></ListGroupItem>
                </ListGroup>
                <Card.Body>
                    <Button variant="primary" size="lg" block>Zakazi savetovanje</Button>{' '}
                    <Button onClick={showMedications} variant="primary" size="lg" block>Vidi dostupne lekove</Button>{' '}
                    <Button onClick={showDerAppointments} variant="primary" size="lg" block>Vidi dostupne termine kod dermatologa</Button>{' '}
                    {actions == null || actions.map(function(v) { return Object.keys(v).length;}) != 0 ? <Button onClick={showActions} variant="primary" size="lg" block>Vidi dostupne akcije</Button> : ' ' }
                    {promotions == null || promotions.map(function(v) { return Object.keys(v).length;}) != 0  ?<Button onClick={showPromotions} variant="primary" size="lg" block>Vidi dostupne promocije</Button> : ' '}
                </Card.Body>
                <Card.Body style={{ width: '65rem'}}>
                    <Fragment>
                    <Map></Map>
                    </Fragment>
                    <Geolocation></Geolocation>
                </Card.Body>
            </Card>
        </CardGroup>

    );
}