import React, { Component } from "react";
import medicationsService from "../common/medications-axios/medications-service";
import promotionsService from "../common/promotion-action-axios/promotions-service";
import rolesService from "../common/roles-axios/roles-services";

class AddPromotion extends Component {
    constructor(props) {
        super(props);
        this.onChangedName = this.onChangedName.bind(this); //vezujes event za poziv funkcije promenljivoj koja pripada klasi
        this.onChangedDescription = this.onChangedDescription.bind(this);
        this.onChangedAmountOfProduct = this.onChangedAmountOfProduct.bind(this);
        this.onChangedPrice = this.onChangedPrice.bind(this);
        this.onChangedEndDate = this.onChangedEndDate.bind(this);
        this.newPromotion = this.newPromotion.bind(this);
        this.savePromotion = this.savePromotion.bind(this);
        this.userId = localStorage.getItem('LogedUser').split(',')[0].split(':')[1];
        //cuvamo podatke kojima cemo pristupati u drugim funkcijama u klasi
        this.state = {
          id: null,
          name: "",
          medicationID: 0,
          description: "",
          amountOfProduct: 0,
          price: 0,
          endDate: "",
          medications: [],
          pharmacyId: -1,
          submitted: false
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

    
      onChangedAmountOfProduct(e) {
        this.setState({
            amountOfProduct: e.target.value
        });
      }

      onChangedPrice(e) {
        this.setState({
          price: e.target.value
        });
      }
      onChangedEndDate(e) {
        this.setState({
          endDate: e.target.value
        });
      }

      savePromotion() {
        var data = {
            id: null,
            name: this.state.name,
            medicationID: this.state.medicationID,
            description: this.state.description,
            amountOfProduct: this.state.amountOfProduct,
            price: this.state.price,
            endDate: this.state.endDate,
            pharmacyId: this.state.pharmacyId
        };

    
        promotionsService.create(data)
          .then(response => {
            this.setState({
              id: response.data.id,
              name: response.data.name,
              medicationID: response.data.medicationID,
              description: response.data.description,
              amountOfProduct: response.data.amountOfProduct,
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

      newPromotion() {
        this.setState({
            id: null,
            name: "",
            medicationID: 0,
            description: "",
            amountOfProduct: 0,
            price: 0,
            endDate: "",
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
        medicationsService.getAll()
        .then(response => {
          let medicationsFromApi = response.data.map(medication => {
            var pharmacyIds = medication.pharmacyid.split(',');
            var i;
            for(i = 0; i < pharmacyIds.length; i += 1){
              if(pharmacyIds[i] == this.state.pharmacyId)
                return { value: medication.id, display: medication.name};
            }
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
        })
      }

      render() {
        return (
          <div className="submit-form">
            {this.state.submitted ? (
              <div>
                <h4>You submitted successfully!</h4>
                <button className="btn btn-success" onClick={this.newPromotion}>
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
                  <label htmlFor="amountOfProduct">Amount Of Product</label>
                  <input
                    type="text"
                    className="form-control"
                    id="amountOfProduct"
                    required
                    value={this.state.amountOfProduct}
                    onChange={this.onChangedAmountOfProduct}
                    name="amountOfProduct"
                  />
                </div>


                <div className="form-group">
                  <label htmlFor="price">Price</label>
                  <input
                    type="text"
                    className="form-control"
                    id="price"
                    required
                    value={this.state.price}
                    onChange={this.onChangedPrice}
                    name="price"
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
    
                <button onClick={this.savePromotion} className="btn btn-success">
                  Submit
                </button>
              </div>
            )}
          </div>
        );
    }
}

export default AddPromotion;
