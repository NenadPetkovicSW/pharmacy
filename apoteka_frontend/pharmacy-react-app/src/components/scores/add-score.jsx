import React, { Component } from "react";
import allergiesService from "../common/allergies-axios/allergies-service";

class AddScore extends Component {
    constructor(props) {
        super(props);
        //poene dodajemo na  nula na pocetku
        this.onChangedName = this.onChangedName.bind(this); //vezujes event za poziv funkcije promenljivoj koja pripada klasi
        this.newAllergie = this.newAllergie.bind(this);
        this.saveAllergie = this.saveAllergie.bind(this);
    
        //cuvamo podatke kojima cemo pristupati u drugim funkcijama u klasi
        this.state = {
          name : ""
        };
      }

      onChangedName(e) {
        this.setState({
          name: e.target.value
        });
      }
      

      saveAllergie() {
      }

      newAllergie() {
      }

      render() {
        return (
          <div className="submit-form">
            {this.state.submitted ? (
              <div>
                <h4>Uspesno dodato!</h4>
                <button className="btn btn-success">
                  Dodaj
                </button>
              </div>
            ) : (
              <div>
                <div className="form-group">
                  <label htmlFor="name">Add score</label>
                  <input
                    type="number"
                    className="form-control"
                    id="name"
                    required
                    value={this.state.name}
                    onChange={this.onChangedName}
                    name="name"
                  />
                </div>
    
                <button className="btn btn-success">
                  Submit
                </button>
              </div>
            )}
          </div>
        );
    }
}

export default AddScore;
