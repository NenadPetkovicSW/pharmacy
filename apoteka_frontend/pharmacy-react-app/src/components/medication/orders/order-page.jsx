import medicationsService from "../../common/medications-axios/medications-service";
import orderService from "../../common/order-axios/order-service";
import React, { useMemo, useState, useEffect } from "react";
import Table from "./current-order-table";
import { Fragment } from "react";
import AddNewMedication from "./new-order";
import AddOldMedication from "./old-order";
import { data } from "jquery";


function OrdersTable({dataTable}){
    const [data, setData] = useState(dataTable);
    // Using useEffect to call the API once mounted and set the data

    const columns = useMemo(
        () => [
          {
            // first group 
            Header: "Order",
            // First group columns
            columns: [
              {
                Header: "Is new medication?",
                accessor: d => d.isNew.toString()
              },
              {
                Header: "Medication name",
                accessor: "name"
              },
              {
                Header: "Price",
                accessor: "price"
              },
              {
                Header: "Amount",
                accessor: "amount"
              }
            ]
          }
        ],
        []
      );
    
    return(
        <Fragment>
        {(data.length == 0)? (''):
            (<Table columns={columns} data={data} /> )
        }
        </Fragment>
    );
}

export default function OrderPage(){
    const [order, setOrders] = useState({
        orders: [

        ]
    });
    const [loaded, setLoaded] = useState(false);
    const [medications, setMedications] = useState([]);
    const [isNew, setIsNew] = useState(false);
    const [submitted, setSubmitted] = useState(false);
    const [submittedAll, setSubmittedAll] = useState(false);
    const pharmacyId = localStorage.getItem('LogedUser').split(',')[0].split(':')[1];
    
    useEffect(() => {
        //imamo sve medications za tu apoteku
        orderService.getAll()
        .then(response => {
                setOrders({
                    orders: [
            
                    ]
                });
                setLoaded(true);
                setSubmitted(false);
            })
        .catch(e => {
            console.log(e);
        });
    }, []);




    const submitOrder = (e) => {
        orderService.create(order)
          .then(response => {
            setSubmittedAll(true);
            console.log(response.data);
            //TODO 
          })
          .catch(e => {
            console.log(e);
          });
    }

    const newMedication = () => {
        setIsNew(true);
        setSubmitted(false);
        setLoaded(false);
    }

    const restockMedication  = () => {
        setIsNew(false);
        setSubmitted(false);
        setLoaded(false);
    }


    const setOrder = (data) =>  {
        order.orders.push(data);
        setSubmitted(true);
    }

    //na submit ide samo stranu nazad
    
    return(
        <Fragment>
            <div className="submit-form">
                {submitted || loaded? (
                <div>
                    <OrdersTable dataTable={order.orders} />
                    <br></br>
                    <button className="btn btn-primary" onClick={newMedication}>
                        Order new medication
                    </button>{' '}
                    <button className="btn btn-primary" onClick={restockMedication}>
                        Restock medication
                    </button>{' '}
                    <button onClick={submitOrder} className="btn btn-success">
                        Send order
                    </button>
                </div>
                ) : (    
                <>
                    {isNew?(<AddNewMedication setOrder={setOrder} submitted={false}/>):(<AddOldMedication setOrder={setOrder} submitted={false}/>)}
                </>
                )}
            </div>
        </Fragment>
        
    );

}