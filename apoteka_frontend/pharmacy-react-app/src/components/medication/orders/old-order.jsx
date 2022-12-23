import React, { Component, useState, useEffect } from "react";
import medicationsService from "../../common/medications-axios/medications-service";
import pharmaciesService from "../../common/pharmacies-axios/pharmacies-service";

export default function AddOldwMedication({ setOrder, submitted }) {
    const [order, setNewOrder] = useState({
        id: null,
        name: "",
        alergiesid: "",
        amount: 0,
        price: 0,
        isNew: true,
        pharmacyid: localStorage.getItem('LogedUser').split(',')[0].split(':')[1],
    });

    const [medications, setMedications] = useState([]);

    useEffect(() => {
        //imamo sve medications za tu apoteku
        medicationsService.getByPharmacyId(localStorage.getItem('LogedUser').split(',')[0].split(':')[1])
        .then(response => {
          let medicationsFromApi = response.data.map(medication => {
                return { value: medication.id, display: medication.name};
          });
          console.log(medicationsFromApi);
          setMedications(
              [{
                value: "",
                display:
                  "(Select medication)"
              }].concat(medicationsFromApi))
          
        })
        .catch(e => {
            console.log(e);
        });
    }, []);

    const onChangedName = (e) => {
        setNewOrder({ ...order, name: e.target.value });
    }

    const onChangedAmount = (e) => {
        setNewOrder({ ...order, amount: e.target.value });
    }

    const onChangedPrice = (e) => {
        setNewOrder({ ...order, price: e.target.value });
    }

    const addToOrder = () => {
        setOrder(order);
    }

    return(
        <div className="submit-form">
                {submitted ? (
                <div>
                    <h4>You added successfully!</h4>
                </div>
                ) : (
                   
                <div>
                    <div className="form-group">
                        <label htmlFor="medication">Medication Id</label>
                        <select
                            value={order.id}
                            onChange={e =>
                            onChangedName(e)}
                        >
                            {medications.map(p => (
                            <option
                                key={p.value}
                                value={p.value}
                            >
                                {p.display}
                            </option>
                            ))}
                        </select>
                    </div>
                    <div className="form-group">
                    <label htmlFor="amount">Amount</label>
                    <input
                        type="text"
                        className="form-control"
                        id="amount"
                        required
                        value={order.amount}
                        onChange={onChangedAmount}
                        name="amount"
                    />
                    </div>


        
                    <button onClick={addToOrder} className="btn btn-success">
                    Add to order
                    </button>
                </div>
                )
            }
        </div>
    );
}
