import {useEffect, useState} from "react";
import { Button, Card, Container, Row } from "react-bootstrap";
import { useHistory } from "react-router-dom";
import pharmacistsService from '../common/pharmacists-axios/pharmacists-service';
import { confirmAlert } from 'react-confirm-alert'; 
import "react-confirm-alert/src/react-confirm-alert.css";
import ReactStars from "react-rating-stars-component";
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

export default function ShowPharmacist(){

    const[data, setData] = useState([]);
    const history = useHistory();
    const [rating, setRating] = useState(3);

    useEffect(() => {
        let id = window.location.pathname.split('/')[2];
        pharmacistsService.get(id)
        .then(response => {
                setData(response.data);
                console.log(response.data);
              })
        .catch(e => {
            console.log(e);
        });
    }, []);

    const deletePharmacist = () => {
        confirmAlert({
            customUI: ({ onClose }) => {
              return (
                <div className='custom-ui'>
                  <h1>Da li ste sigurni?</h1>
                  <p>Da li zelite da obrisete ovog farmaceuta?</p>
                  <Button variant="outline-light" onClick={onClose}>Ne</Button>{'  '}
                  <Button variant="outline-light"
                    onClick={() => {
                        let id = window.location.pathname.split('/')[2];
                        pharmacistsService.delete(id)
                        .then(response => {
                            onClose();
                                history.push(`/pharmacists/`);
                                console.log(response.data);
                              })
                        .catch(e => {
                            console.log(e);
                        });
                      
                    }}
                  >
                    Da, obrisi!
                  </Button>
                </div>
              );
            }
          });
    };

    

    return(
        <Container>
            <Row sm={12}>
                <Card sm={8}>
                    <Card.Header>Farmaceut</Card.Header>
                    <Card.Body>
                        <Card.Title>{data.firstName}</Card.Title>
                        <Card.Text>
                        Ako vam je potrebna konsultacija kliknite dugme ispod.
                        </Card.Text>
                        <Card.Text>
                        <StarRating 
                          count={5}
                          size={40}
                          value={rating}
                          activeColor ={'red'}
                          inactiveColor={'#ddd'}
                            />

                        </Card.Text>
                        <Button variant="primary">Zakazivanje termina</Button>{' '}
                        <Button variant="primary" onClick={() => deletePharmacist()}>Brisanje farmaceuta</Button>
                    </Card.Body>
                </Card>
                <Card sm={4} style={{width: 500, legth: 100}}>
                    <Card.Img variant="top" src="https://images.unsplash.com/photo-1580281658223-9b93f18ae9ae?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80" />
                </Card>
            </Row>
        </Container>

    );
}