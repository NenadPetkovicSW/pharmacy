import React, { Component } from "react";
import usersService from "../common/user-axios/user-service";
import { useHistory } from "react-router-dom";
import allergieServices from '../common/allergies-axios/allergies-service';
import ReactDOM from 'react-dom';

class EditUser extends Component {
    constructor(props) {
        super(props);
        //poene dodajemo na  nula na pocetku
        this.onChangedUsername = this.onChangedUsername.bind(this); //vezujes event za poziv funkcije promenljivoj koja pripada klasi
        this.onChangedEmail = this.onChangedEmail.bind(this);
        this.onChangedPassword = this.onChangedPassword.bind(this);
        this.onChangedFirstName = this.onChangedFirstName.bind(this);
        this.onChangedLastName = this.onChangedLastName.bind(this);
        this.onChangedAlergiesIds = this.onChangedAlergiesIds.bind(this);
        this.newUser = this.newUser.bind(this);
        this.editUser = this.editUser.bind(this);



        //cuvamo podatke kojima cemo pristupati u drugim funkcijama u klasi
        this.state = {
          username: "",
          email: "", 
          password: "",
          firstName: "",
          lastName: "",
          alergiesIds: "",
          submitted: false
        };
      }

      componentDidMount(){
        usersService.get(window.location.pathname.split('/')[2])
          .then(response => {
            this.setState({
              username: response.data.username,
              email: response.data.email, 
              password: response.data.password,
              firstName: response.data.firstName,
              lastName: response.data.lastName,
              alergiesIds: response.data.alergiesIds,
              submitted: false
            });
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
      }


      onChangedUsername(e) {
        this.setState({
          username: e.target.value
        });
      }
      onChangedEmail(e) {
        this.setState({
          email: e.target.value
        });
      }
      onChangedPassword(e) {
        this.setState({
          password: e.target.value
        });
      }
      onChangedFirstName(e) {
        this.setState({
          firstName: e.target.value
        });
      }
      onChangedLastName(e) {
        this.setState({
          lastName: e.target.value
        });
      }
      onChangedAlergiesIds(e) {
        this.setState({
          alergiesIds: e.target.value
        });
      }

      editUser() {
        var data = {
            username: this.state.username,
            email: this.state.email, 
            password: this.state.password,
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            alergiesIds: this.state.alergiesIds
        };
        
        usersService.update(window.location.pathname.split('/')[2],data)
          .then(response => {
            this.setState({
              username: response.data.username,
              email: response.data.email, 
              password: response.data.password,
              firstName: response.data.firstName,
              lastName: response.data.lastName,
              alergiesIds: response.data.alergiesIds,
              submitted: true
            });
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });

       
      }

      newUser() {
        this.setState({
            password: "", 
            firstName: "",
            lastName: "",
            submitted: false
        });
      }
       

      render() {
        const { history } = this.props


    // Using useEffect to call the API once mounted and set the data


        return (
          <div className="submit-form">
            {this.state.submitted ? (
              <div>
                <h4>Uspesno izmenjeno!</h4>
                <button className="btn btn-success" onClick={() => history.push(`/profile/${window.location.pathname.split('/')[2]}`)}>
                  Idi nazad
                </button>
              </div>
            ) : (
              <div>
    
                <div className="form-group">
                  <label htmlFor="email">Email</label>
                  <input
                    type="text"
                    className="form-control"
                    id="email"
                    required
                    value={this.state.email}
                    onChange={this.onChangedEmail}
                    name="email"
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="firstName">First Name</label>
                  <input
                    type="text"
                    className="form-control"
                    id="firstName"
                    required
                    value={this.state.firstName}
                    onChange={this.onChangedFirstName}
                    name="firstName"
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="lastName">Last Name</label>
                  <input
                    type="text"
                    className="form-control"
                    id="lastName"
                    required
                    value={this.state.lastName}
                    onChange={this.onChangedLastName}
                    name="lastName"
                  />
                </div>

          

                <button onClick={this.editUser} className="btn btn-success">
                  Submit
                </button>
              </div>
            )}
          </div>
        );
    }
}

export default EditUser;
