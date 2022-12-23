import React, { Component } from "react";
import medicationsService from "../common/medications-axios/medications-service";
import pharmaciesService from "../common/pharmacies-axios/pharmacies-service";

class AddMedication extends Component {
    constructor(props) {
        super(props);
        //poene dodajemo na  nula na pocetku
        this.onChangedName = this.onChangedName.bind(this); //vezujes event za poziv funkcije promenljivoj koja pripada klasi
        this.onChangePharmacy = this.onChangePharmacy.bind(this);
        this.newMedication = this.newMedication.bind(this);
        this.saveMedication = this.saveMedication.bind(this);
    
        //cuvamo podatke kojima cemo pristupati u drugim funkcijama u klasi
        this.state = {
          id: null,
          name: "",
          pharmacies: [],
          pharmacyid: "",
          submitted: false
        };
      }

      onChangedName(e) {
        this.setState({
            name: e.target.value
        });
      }

    
      onChangePharmacy(e) {
        this.setState({
          pharmacyid: e.target.value
        });
      }

      saveMedication() {
        var data = {
            id: null,
            name: this.state.name,
            pharmacyid: this.state.pharmacyid,
            price: 100
        };
    
        medicationsService.create(data)
          .then(response => {
            this.setState({
              id: response.data.id,
              name: response.data.name,
              pharmacyid: response.data.pharmacy,
              submitted: true
            });
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });

       
      }

      newMedication() {
        this.setState({
            id: null,
            name: "",
            pharmacyid: "",
            submitted: false
        });
      }

      componentDidMount() {
        pharmaciesService.getAll()
        .then(response => {
          let pharmaciesFromApi = response.data.map(pharmacy => {
            return { value: pharmacy.id, display: pharmacy.name };
          });
          console.log(pharmaciesFromApi);
          this.setState({
            pharmacies: [
              {
                value: "",
                display:
                  "(Select pharmacy)"
              }
            ].concat(pharmaciesFromApi)
          })
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
                <button className="btn btn-success" onClick={this.newMedication}>
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
                  <label htmlFor="pharmacy">Pharmacy Id</label>
                  <select
                    value={this.state.pharmacyid}
                    onChange={e =>
                      this.setState({
                        pharmacyid: e.target.value,
                        validationError:
                          e.target.value === ""
                            ? "You must select o"
                            : ""
                      })}
                  >
                    {this.state.pharmacies.map(p => (
                      <option
                        key={p.value}
                        value={p.value}
                      >
                        {p.display}
                      </option>
                    ))}
                  </select>
                </div>
    
                <button onClick={this.saveMedication} className="btn btn-success">
                  Submit
                </button>
              </div>
            )}
          </div>
        );
    }
}

export default AddMedication;
