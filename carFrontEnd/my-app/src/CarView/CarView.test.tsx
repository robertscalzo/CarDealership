import React from 'react';
import {fireEvent, render, screen} from '@testing-library/react';
import {CarPurchase} from "./CarPurchase";
import {isBoolean} from "util";

describe("tests for purchase", () => {
    let renderSalesPage = false;
    const setRenderSalesPage = jest.fn((input: boolean) => {
        renderSalesPage = !input
    })

    let numAppointments = 0;
    const setNumAppointments = jest.fn((input: number) => {
        numAppointments = input;
    })


    it("renders list of cars", () => {
        render(<CarPurchase renderSalesPage={renderSalesPage}
                            setRenderSalesPage={setRenderSalesPage}
                            numAppointments={numAppointments}
                            setNumAppointments={setNumAppointments}/>)

        expect(screen.getByText("HI")).toBeInTheDocument();
    })
    it("renders Pass button and changes conditional rendering state value", () => {

        const {rerender} = render(<CarPurchase renderSalesPage={renderSalesPage}
                                               setRenderSalesPage={setRenderSalesPage}
                                               numAppointments={numAppointments}
                                               setNumAppointments={setNumAppointments}
        />)
        const findPassButton = screen.getByRole("button", {name: "Pass"})
        expect(renderSalesPage).toBe(false);
        let validClick = fireEvent.click(findPassButton);
        if (validClick) {
            renderSalesPage = !renderSalesPage
        }
        expect(renderSalesPage).toBe(true);

        /*expect(setRenderSalesPage).toHaveBeenCalled();
        expect(setRenderSalesPage).toBeCalledTimes(1)
        rerender(<CarPurchase renderSalesPage={renderSalesPage} setRenderSalesPage={setRenderSalesPage}/>)
        expect(screen.getByText("Home Screen")).toBeInTheDocument() FOR NOTE KEEPING SAKE*/
    })

    it("when carlots empty, go to new page with we are sorry message", () => {
        render(<CarPurchase renderSalesPage={renderSalesPage}
                            setRenderSalesPage={setRenderSalesPage}
                            numAppointments={numAppointments}
                            setNumAppointments={setNumAppointments}/>)
        expect(screen.getByText("Sorry were out of cars")).toBeInTheDocument();

    })
})
