import React, {useEffect, useState} from 'react';
import {getCars, purchaseCar} from "./CarAPI";
import {Car} from "./Car";

interface CarPurchaseProps {
    renderSalesPage: boolean,
    setRenderSalesPage: Function,
    setNumAppointments: Function,
    numAppointments: number,
}

export const CarPurchase: React.FC<CarPurchaseProps> = ({
                                                            renderSalesPage,
                                                            setRenderSalesPage,
                                                            setNumAppointments,
                                                            numAppointments
                                                        }) => {

    const [carList, setCarList] = useState<Car[]>([])

    const loadCars = () => {
        getCars().then((data) => setCarList(data));
    }

    useEffect(loadCars, []);

    const purchase = (id: number) => {
        purchaseCar(id)
        setCarList(carList.filter(car => car.id !== id))
    }

    const renderCarListOrSorry = (setNumAppointments: Function, numAppointments: number) => carList.length == 0 ? (
        <div>Sorry were out of cars</div>) : (carList.map((car) => {
        return (<div>
            <li key={car.id}>Make: {car.make} Model: {car.model} Price: {car.price}</li>
            <button onClick={() => {
                purchase(car.id);
                setNumAppointments(numAppointments + 1);
            }}>
                Purchase
            </button>
        </div>)
    }))


    return (
        <div>
            <>HI</>

            {renderCarListOrSorry(setNumAppointments, numAppointments)}

            <button onClick={() => {
                setRenderSalesPage(!renderSalesPage)
                setNumAppointments(numAppointments + 1)
            }}>
                Pass
            </button>
        </div>
    )
}
