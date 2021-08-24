import React from 'react';
import {httpClient} from "../HTTPClient";
import {Car} from "./Car";


export const getCars = async (): Promise<Car[]> => {
    const listCars = await httpClient.get("http://lccalhost:8080/car/")
    return listCars.data;
}

export const purchaseCar = async (id: number): Promise<void> => {
    await httpClient.delete("http://lccalhost:8080/car/" + id);
}

export const getCommission = async ():Promise<number> =>{
    const totalCommission=await httpClient.get("http://lccalhost:8080/car/commission/")
    return totalCommission.data;
}
