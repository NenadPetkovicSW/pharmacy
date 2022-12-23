import React, { Component } from "react";
import usersService from "../common/user-axios/user-service";
import { useHistory } from "react-router-dom";
import allergieServices from '../common/allergies-axios/allergies-service';
import ReactDOM from 'react-dom';

class EditPassword extends Component {
      constructor(props) {
        super(props);
        //poene dodajemo na  nula na pocetku
        this.onChangedPassword = this.onChangedPassword.bind(this);
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
              password: "",
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
      onChangedPassword(e) {
        this.setState({
          password: e.target.value
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
                  <label htmlFor="password">Password</label>
                  <input
                    type="text"
                    className="form-control"
                    id="password"
                    required
                    value={this.state.password}
                    onChange={this.onChangedPassword}
                    name="password"
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

export default EditPassword;
