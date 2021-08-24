import React, {useEffect, useState} from 'react';
import {CarPurchase} from "./CarView/CarPurchase";
import {getCommission} from "./CarView/CarAPI";


export const App = () => {
    const [numAppointments, setNumAppointments] = useState(0);
    const [renderSalesPage, setRenderSalesPage] = useState(false);
    const [commission, setCommission]=useState<number>(0);


    const loadCommision =()=>{
        getCommission().then(data=>setCommission(data))
    }
    useEffect(()=>{
      if(numAppointments>4){
          console.log("fetch")
             loadCommision();
      }

      },[numAppointments])

    const PurchaseOrCommission = (numAppointments: number) => {
        if (numAppointments < 5) {
            return (
                <button onClick={() => {
                    setRenderSalesPage(!renderSalesPage);
                }}>
                    Purchase Cars
                </button>
            )
        }
        return (
            <div>
            ${commission}
            </div>
        )
    }

    if (renderSalesPage) {
        return <CarPurchase setNumAppointments={setNumAppointments} numAppointments={numAppointments} renderSalesPage={renderSalesPage} setRenderSalesPage={setRenderSalesPage}/>;
    } else {
        return (
            <div>
                Home Screen
                {PurchaseOrCommission(numAppointments)}

            </div>);
    }
}

