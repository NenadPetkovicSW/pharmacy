import React, { Component } from "react";
import allergiesService from "../common/allergies-axios/allergies-service";

class AddAllergies extends Component {
    constructor(props) {
        super(props);
        //poene dodajemo na  nula na pocetku
        this.onChangedName = this.onChangedName.bind(this); //vezujes event za poziv funkcije promenljivoj koja pripada klasi
        this.newAllergie = this.newAllergie.bind(this);
        this.saveAllergie = this.saveAllergie.bind(this);
    
        //cuvamo podatke kojima cemo pristupati u drugim funkcijama u klasi
        this.state = {
          id: null,
          name: "",
          submitted: false
        };
      }

      onChangedName(e) {
        this.setState({
          name: e.target.value
        });
      }
      

      saveAllergie() {
        var data = {
            id: null,
            name: this.state.name,
        };
    
        allergiesService.create(data)
          .then(response => {
            this.setState({
              id: response.data.id,
              name: response.data.name,
              submitted: true
            });
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });

       
      }

      newAllergie() {
        this.setState({
            id: null,
            name: "",
            submitted: false
        });
      }

      render() {
        return (
          <div className="submit-form">
            {this.state.submitted ? (
              <div>
                <h4>Uspesno dodato!</h4>
                <button className="btn btn-success" onClick={this.newAllergie}>
                  Dodaj
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
    
                
    
                <button onClick={this.saveAllergie} className="btn btn-success">
                  Submit
                </button>
              </div>
            )}
          </div>
        );
    }
}

export default AddAllergies;
