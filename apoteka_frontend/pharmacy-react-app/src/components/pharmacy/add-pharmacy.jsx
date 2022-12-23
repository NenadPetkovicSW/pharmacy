import React, { Component } from "react";
import pharmaciesService from "../common/pharmacies-axios/pharmacies-service";

class AddPharmacy extends Component {
    constructor(props) {
        super(props);
        //poene dodajemo na  nula na pocetku
        this.onChangedName = this.onChangedName.bind(this); //vezujes event za poziv funkcije promenljivoj koja pripada klasi
        this.onChangedAddress = this.onChangedAddress.bind(this);
        this.newPharmacy = this.newPharmacy.bind(this);
        this.savePharmacy = this.savePharmacy.bind(this);
    
        //cuvamo podatke kojima cemo pristupati u drugim funkcijama u klasi
        this.state = {
          id: null,
          name: "",
          address: "", 
          points: 0,
          submitted: false
        };

        
      }

      onChangedName(e) {
        this.setState({
            name: e.target.value
        });
      }
    
      onChangedAddress(e) {
        this.setState({
            address: e.target.value
        });
      }

      savePharmacy() {
        var data = {
            id: null,
            name: this.state.name,
            address: this.state.address,
            average_score: 0
        };
    
        pharmaciesService.create(data)
          .then(response => {
            this.setState({
              id: response.data.id,
              name: response.data.name,
              address: response.data.address,
              points: response.data.points,
              submitted: true
            });
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });

       
      }

      newPharmacy() {
        this.setState({
            id: null,
            name: "",
            address: "", 
            points: 0,
            submitted: false
        });
      }

      render() {
        return (
          <div className="submit-form">
            {this.state.submitted ? (
              <div>
                <h4>You submitted successfully!</h4>
                <button className="btn btn-success" onClick={this.newPharmacy}>
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
                  <label htmlFor="address">Address</label>
                  <input
                    type="text"
                    className="form-control"
                    id="address"
                    required
                    value={this.state.address}
                    onChange={this.onChangedAddress}
                    name="address"
                  />
                </div>
    
                <button onClick={this.savePharmacy} className="btn btn-success">
                  Submit
                </button>
              </div>
            )}
          </div>
        );
    }
}

export default AddPharmacy;
