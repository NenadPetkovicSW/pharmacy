import React, { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import LoginServices from "../common/login-axios/login-service"
import RoleServices from "../common/roles-axios/roles-services";
import "../css/login.css";


export default function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  const [userLog, setUserLog] = useState([]);
  const [submited, setSubmited] = useState(false);
  const [loading, setLoading] = useState(false);
  const [loading2, setLoading2] = useState(false);
  const [user, setUser] = useState([]);
  const [role, setRole] = useState([]);
  const history = useHistory();


  function validateForm() {
    return !(username.length > 0 && password.length > 0);
  }

  

    useEffect(() => {
    LoginServices.login(userLog.username,userLog.password)
    .then(response => {
      setUser(response.data);
      setLoading(true);
            console.log(response.data);
            })
    .catch(e => {
        console.log(e);
        if(submited == true){
        setMessage("Pogresna sifra ili username!");
        setSubmited(false);
        }
    });

    
  }, [userLog]);
  


  useEffect(() => {
    if(loading == true){
    RoleServices.getUserRole(user.id)
    .then(response => {
      setRole(response.data);
            console.log(response.data);
            setLoading2(true);
            })
    .catch(e => {
        console.log(e);
    });
  }
    
  }, [loading]);


  useEffect(() => {
    if(loading2 == true)
    {
    if(user.id == null)
    {
      setMessage("Pogresna sifra ili username!");
      setSubmited(false);
    }
    else
    {
    setMessage("");
    
    // store the user in localStorage
    localStorage.setItem('LogedUser', JSON.stringify(user));
    console.log(user);

    //let pomocna = JSON.parse(localStorage.getItem('LogedUser'));
    //alert(pomocna.username);


    localStorage.setItem('LogedRole', String(role.role));
    console.log(role.role);
    //alert(localStorage.getItem('LogedRole'));


    alert("Uspesna prijava");
    history.push("/");
    history.go(0);
    }
  }
    
  }, [loading2]);

  
  function handleSubmit(event) {
    event.preventDefault();
    setSubmited(true);
    setLoading(false);
    setLoading2(false);
    
    setUser([]);
    setRole("");
    setUserLog({ username, password });
    
    // send the username and password to the server

  }


  return (
    <div className="Login">
      <Form onSubmit={handleSubmit}>
        <Form.Group size="lg" controlId="username">
          <Form.Label>Username</Form.Label>
          <Form.Control
            autoFocus
            type="username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </Form.Group>
        <Form.Group size="lg" controlId="password">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </Form.Group>
        <Form.Label id="message">{message}</Form.Label>
        <Button block size="lg" type="get" disabled={validateForm} onSubmit={handleSubmit}>
          Login
        </Button>
      </Form>
    </div>
  );
}