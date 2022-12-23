import React, { Component } from "react";
import { ThemeConsumer } from "react-bootstrap/esm/ThemeProvider";
import dermatologistService from '../common/dermatologists-axios/dermatologists-service';
import pharmaciesService from "../common/pharmacies-axios/pharmacies-service";

class AddDermatologist extends Component{
    constructor(props) {
        super(props);
        //poene dodajemo na  nula na pocetku
        this.onChangedName = this.onChangedName.bind(this); //vezujes event za poziv funkcije promenljivoj koja pripada klasi
        this.onChangeLastName = this.onChangeLastName.bind(this);
        this.onChangePharmacy = this.onChangePharmacy.bind(this);
        this.newDermatologist = this.newDermatologist.bind(this);
        this.saveDermatologist = this.saveDermatologist.bind(this);
    
        //cuvamo podatke kojima cemo pristupati u drugim funkcijama u klasi
        this.state = {
          id: null,
          name: "",
          lastName: "", 
          pharmacy: "",
          submitted: false,
          pharmacies: []
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
    
      onChangePharmacy(e) {
        this.setState({
          pharmacy: e.target.value
        });
      }

      saveDermatologist() {
        var data = {
            id: null,
            firstName: this.state.name,
            lastName: this.state.lastName,
            pharmacy: this.state.pharmacy
        };
    
        dermatologistService.create(data)
          .then(response => {
            this.setState({
              id: response.data.id,
              name: response.data.firstName,
              lastName: response.data.lastName,
              pharmacy: response.data.pharmacy,
              submitted: true
            });
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });

       
      }

      newDermatologist() {
        this.setState({
            id: null,
            name: "",
            lastName: "", 
            pharmacyId: "",
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
                <button className="btn btn-success" onClick={this.newDermatologist}>
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
                <label htmlFor="pharmacy">Pharmacy Id</label>
                  <select
                    value={this.state.pharmacy}
                    onChange={e =>
                      this.setState({
                        pharmacy: e.target.value,
                        validationError:
                          e.target.value === ""
                            ? "You must select your favourite team"
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
    
                <button onClick={this.saveDermatologist} className="btn btn-success">
                  Submit
                </button>
              </div>
            )}
          </div>
        );
    }
}

export default AddDermatologist;