import {Card, ListGroup, ListGroupItem, Button, CardGroup, ButtonGroup} from 'react-bootstrap';
import Image from 'react-bootstrap/Image'
import {useEffect, useState} from "react";
import usersService from '../common/user-axios/user-service';
import allergiesService from "../common/allergies-axios/allergies-service"
import { useHistory } from "react-router-dom";

export default function ShowProfile(){

    const[data, setData] = useState([]);
    const[alergies, setAlergies] = useState([]);
    const history = useHistory();
    var labelText = "";
    useEffect(() => {
        let id = window.location.pathname.split('/')[2];
        usersService.get(id)
        .then(response => {
                setData(response.data);
                console.log(response.data);
              })
        .catch(e => {
            console.log(e);
        });
    }, []);

    useEffect(() => {

        allergiesService.getAll()
        .then(response => {
                setAlergies(response.data);
                console.log(response.data);
              })
        .catch(e => {
            console.log(e);
        });
    }, []);


    function alergijeDodaj()
    {
        let pids = String(data.alergiesIds).split(" ").join("");

        let pids_array = pids.split(",");

        let pom = pids_array.length;

        labelText = "";

        for(let i = 0; i<alergies.length; i++)
        {

            if(pids_array.includes(String(alergies[i].id)))
            {
                pom--;
                
                labelText = labelText + String(alergies[i].name);

                if(pom == 0)
                    break;
                else
                labelText = labelText + ",";
            }

        }

        return labelText;
    }

    const editProfile = () => {
        history.push(`/profile-edit/${data.id}`);
    };


    return(

        <CardGroup>
            <Image src="https://s.pngix.com/pngfile/s/649-6497838_patient-png-patient-cartoon-png-transparent-png.png"  roundedCircle ></Image>
            <Card>
                <Card.Body>
                    <Card.Title>{data.username}</Card.Title>
                    <Card.Text>
                    Profil
                    </Card.Text>
                </Card.Body>
                <ListGroup className="list-group-flush">
                    <ListGroupItem>First name: {data.firstName}</ListGroupItem>
                    <ListGroupItem>Last name: {data.lastName}</ListGroupItem>
                    <ListGroupItem>Email: {data.email}</ListGroupItem>
                    <ListGroupItem>Allegies: {alergijeDodaj()}</ListGroupItem>
                    <Button onClick={editProfile} variant="primary" size="lg" block>Edit</Button>{' '}
                </ListGroup>
            </Card>
        </CardGroup>

    );
}