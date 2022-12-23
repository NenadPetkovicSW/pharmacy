import React, { Component } from "react";
import medicationsService from "../common/medications-axios/medications-service";
import actionsService from "../common/promotion-action-axios/actions-service";
import rolesService from "../common/roles-axios/roles-services";

class AddAction extends Component {
    
    constructor(props) {
        super(props);
        this.onChangedName = this.onChangedName.bind(this); //vezujes event za poziv funkcije promenljivoj koja pripada klasi
        this.onChangedDescription = this.onChangedDescription.bind(this);
        this.onChangedNewPrice = this.onChangedNewPrice.bind(this);
        this.onChangedEndDate = this.onChangedEndDate.bind(this);
        this.newAction = this.newAction.bind(this);
        this.saveAction = this.saveAction.bind(this);
        this.userId = localStorage.getItem('LogedUser').split(',')[0].split(':')[1];
        //cuvamo podatke kojima cemo pristupati u drugim funkcijama u klasi
        this.state = {
          name: "",
          medicationID: 0,
          description: "",
          newPrice: 0,
          medications: [],
          submitted: false,
          pharmacyId: -1,
        };
      }

      onChangedName(e) {
        this.setState({
            name: e.target.value
        });
      }

      onChangedDescription(e) {
        this.setState({
            description: e.target.value
        });
      }

      onChangedEndDate(e) {
        this.setState({
          endDate: e.target.value
        });
      }

      onChangedNewPrice(e) {
        this.setState({
          newPrice: e.target.value
        });
      }

      saveAction() {
        var data = {
            id: null,
            name: this.state.name,
            medicationID: this.state.medicationID,
            description: this.state.description,
            price: 0,
            newPrice: this.state.newPrice,
            endDate: this.state.endDate,
            pharmacyId: this.state.pharmacyId
        };

    
        actionsService.create(data)
          .then(response => {
            this.setState({
              id: response.data.id,
              name: response.data.name,
              medicationID: response.data.medicationID,
              description: response.data.description,
              newPrice: response.data.newPrice,
              price: response.data.price,
              pharmacyId: response.data.pharmacyId,
              submitted: true
            });
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
      }

      newAction() {
        this.setState({
            name: "",
            medicationID: 0,
            description: "",
            newPrice: 0,
            submitted: false
        });
      }

      componentDidMount() {
        rolesService.get(this.userId).then(response => {
          this.setState({
            pharmacyId: response.data.pharmacyId
          });
        })
        .catch(e => {
            console.log(e);
        });

        medicationsService.getByPharmacyId(this.state.pharmacyId)
        .then(response => {
          let medicationsFromApi = response.data.map(medication => {
                return { value: medication.id, display: medication.name};
          });
          console.log(medicationsFromApi);
          this.setState({
            medications: [
              {
                value: "",
                display:
                  "(Select medication)"
              }
            ].concat(medicationsFromApi)
          })
        })
        .catch(e => {
            console.log(e);
        });
          
      }

      render() {
        return (
          <div className="submit-form">
            {this.state.submitted ? (
              <div>
                <h4>You submitted successfully!</h4>
                <button className="btn btn-success" onClick={this.newAction}>
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
                  <label htmlFor="description">Description</label>
                  <input
                    type="text"
                    className="form-control"
                    id="description"
                    required
                    value={this.state.description}
                    onChange={this.onChangedDescription}
                    name="description"
                  />
                </div>

        
                <div className="form-group">
                  <label htmlFor="endDate">End date</label>
                  <input
                    type="text"
                    className="form-control"
                    id="endDate"
                    required
                    value={this.state.endDate}
                    onChange={this.onChangedEndDate}
                    name="endDate"
                  />
                </div>



                <div className="form-group">
                  <label htmlFor="newPrice">New price</label>
                  <input
                    type="text"
                    className="form-control"
                    id="newPrice"
                    required
                    value={this.state.newPrice}
                    onChange={this.onChangedNewPrice}
                    name="newPrice"
                  />
                </div>

                
                <div className="form-group">
                  <label htmlFor="pharmacy">Medication Id</label>
                  <select
                    value={this.state.medicationID}
                    onChange={e =>
                      this.setState({
                        medicationID: e.target.value,
                        validationError:
                          e.target.value === ""
                            ? "You must select onne"
                            : ""
                      })}
                  >
                    {this.state.medications.map(p => (
                      <option
                        key={p.value}
                        value={p.value}
                      >
                        {p.display}
                      </option>
                    ))}
                  </select>
                </div>
    
                <button onClick={this.saveAction} className="btn btn-success">
                  Submit
                </button>
              </div>
            )}
          </div>
        );
    }
}

export default AddAction;
