import {Card, ListGroup, ListGroupItem, Button, CardGroup, ButtonGroup} from 'react-bootstrap';
import Image from 'react-bootstrap/Image'
import {useEffect, useState} from "react";
import usersService from '../common/user-axios/user-service';
import allergiesService from "../common/allergies-axios/allergies-service"
import { useHistory } from "react-router-dom";
import "../css/myProfile.css"
export default function ShowProfileAdmin(){

    const[data, setData] = useState([]);
    const[alergies, setAlergies] = useState([]);
    const[rolepom, setRolepom] = useState();
    const history = useHistory();
    var labelText = "";


    useEffect(() => {
        let pomocna = JSON.parse(localStorage.getItem('LogedUser'));
        //alert(pomocna.username);
        let pom_role =  localStorage.getItem('LogedRole');
        setData(pomocna);
        setRolepom(pom_role);
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

    const editPassword = () => {
        history.push(`/password-edit/${data.id}`);
    };


    return(
    <div>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css" />
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    
    <div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-6 col-md-6" id="div-center">
            <div class="well well-sm">
                <div class="row">
                    <div class="col-sm-6 col-md-4">
                        <img src="https://s.pngix.com/pngfile/s/649-6497838_patient-png-patient-cartoon-png-transparent-png.png" alt="" id="imgMyProfile" class="img-rounded-circle img-responsive" />
                    </div>
                    <div class="col-sm-6 col-md-8">
                        <h4>
                            {data.firstName} {data.lastName}</h4>
                        <p>
                            <i class="glyphicon glyphicon-user"></i>{data.username}
                            <br />
                            <i class="glyphicon glyphicon-envelope"></i>{data.email}
                            <br />
                            <i class="glyphicon glyphicon-plus"></i>{alergijeDodaj()}
                            <br />
                            <i class="glyphicon glyphicon-tower"></i>{rolepom}
                        </p>
                        
                        <div class="btn-group">
                            <button type="button" class="btn btn-primary" onClick={editProfile}>
                                Izmena profila
                            </button>
                        </div>
                        <div class="btn-group">
                            <button type="button" class="btn btn-primary" onClick={editPassword}>
                                Izmena sifre
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</div>
    );
}