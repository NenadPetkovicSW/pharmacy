import React, { Component } from "react";
import { ThemeConsumer } from "react-bootstrap/esm/ThemeProvider";
import pharmaciesService from "../common/pharmacies-axios/pharmacies-service";
import pharmacistsService from '../common/pharmacists-axios/pharmacists-service';

class AddPharmacist extends Component{
    constructor(props) {
        super(props);
        //poene dodajemo na  nula na pocetku
        this.onChangedName = this.onChangedName.bind(this); //vezujes event za poziv funkcije promenljivoj koja pripada klasi
        this.onChangeLastName = this.onChangeLastName.bind(this);
        this.onChangeStartTime = this.onChangeStartTime.bind(this);
        this.onChangeEndTime = this.onChangeEndTime.bind(this);
        this.newPharmacist = this.newPharmacist.bind(this);
        this.savePharmacist = this.savePharmacist.bind(this);
    
        //cuvamo podatke kojima cemo pristupati u drugim funkcijama u klasi
        this.state = {
          id: null,
          name: "",
          lastName: "", 
          pharmacy: localStorage.getItem('LogedUser').split(',')[0].split(':')[1],
          startTime: "",
          endTime: "",
          pharmacies: [],
          submitted: false
        };
      }

      onChangedName(e) {
        this.setState({
            name: e.target.value
        });
      }

      onChangeLastName(e) {
        this.setState({
            lastName: e.target.value
        });
      }

      onChangeStartTime(e) {
        this.setState({
            startTime: e.target.value
        });
      }

      onChangeEndTime(e) {
        this.setState({
            endTime: e.target.value
        });
      }

      savePharmacist() {
        var data = {
            id: null,
            firstName: this.state.name,
            lastName: this.state.lastName,
            pharmacy: this.state.pharmacy,
            startTime: this.state.startTime,
            endTime: this.state.endTime
        };
    
        pharmacistsService.create(data)
          .then(response => {
            this.setState({
              id: response.data.id,
              name: response.data.firstName,
              lastName: response.data.lastName,
              pharmacy: response.data.pharmacy,
              startTime: this.state.startTime,
              endTime: this.state.endTime,
              submitted: true
            });
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });

       
      }

      newPharmacist() {
        this.setState({
            id: null,
            name: "",
            lastName: "", 
            pharmacy: localStorage.getItem('LogedUser').split(',')[0].split(':')[1],
            startTime: this.state.startTime,
            endTime: this.state.endTime,
            submitted: false
        });
      }

      componentDidMount() {
        pharmaciesService.getAll()
        .then(response => {
          let pharmaciesFromApi = response.data.map(pharmacy => {
            return { value: pharmacy.id, display: pharmacy.name };
          });
          this.setState({
            pharmacies: [
              {
                value: "",
                display:
                  "(Select pharmacy)"
              }
            ].concat(pharmaciesFromApi)
          });
        })
        .catch(e => {
            console.log(e);
        })
      }

      render() {
        return (
          <div className="submit-form">
            {this.state.submitted ? (
              <div>
                <h4>You submitted successfully!</h4>
                <button className="btn btn-success" onClick={this.newPharmacist}>
                  Add
                </button>
              </div>
            ) : (
              <div>
                <div className="form-group">
                  <label htmlFor="name">Name</label>
                  <input
                    type="text"
                    className="form-control"
                    id="name"
                    required
                    value={this.state.name}
                    onChange={this.onChangedName}
                    name="name"
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
                    onChange={this.onChangeLastName}
                    name="lastName"
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="lastName">Start time (HH:mm:ss)</label>
                  <input
                    type="text"
                    className="form-control"
                    id="startTime"
                    required
                    value={this.state.startTime}
                    onChange={this.onChangeStartTime}
                    name="startTime"
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="lastName">End time (HH:mm:ss)</label>
                  <input
                    type="text"
                    className="form-control"
                    id="endTime"
                    required
                    value={this.state.endTime}
                    onChange={this.onChangeEndTime}
                    name="endTime"
                  />
                </div>
    
                <button onClick={this.savePharmacist} className="btn btn-success">
                  Submit
                </button>
              </div>
            )}
          </div>
        );
    }
}

export default AddPharmacist;