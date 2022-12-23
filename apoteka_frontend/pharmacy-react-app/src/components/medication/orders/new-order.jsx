import React, { Component, useState } from "react";
import medicationsService from "../../common/medications-axios/medications-service";
import pharmaciesService from "../../common/pharmacies-axios/pharmacies-service";

export default function AddNewMedication({ setOrder, submitted }) {
    const [order, setNewOrder] = useState({
        id: null,
        name: "",
        alergiesid: "",
        amount: 0,
        price: 0,
        isNew: true,
        pharmacyid: localStorage.getItem('LogedUser').split(',')[0].split(':')[1],
    });

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
                    <label htmlFor="name">Name</label>
                    <input
                        type="text"
                        className="form-control"
                        id="name"
                        required
                        value={order.name}
                        onChange={onChangedName}
                        name="name"
                    />
                    </div>
    
                    
                    <div className="form-group">
                    <label htmlFor="price">Price</label>
                    <input
                        type="text"
                        className="form-control"
                        id="price"
                        required
                        value={order.price}
                        onChange={onChangedPrice}
                        name="price"
                    />
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


