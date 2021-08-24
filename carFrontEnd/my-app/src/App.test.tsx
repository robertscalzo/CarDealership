import React from 'react';
import {fireEvent, render, screen} from '@testing-library/react';
import '@testing-library/jest-dom';
import {App} from './App';

describe("App.tsx tests for homescreen functionality",()=>{
it('renders learn react link', () => {
  render(<App />);
  expect(screen.getByText("Home Screen")).toBeInTheDocument();
});

it("click button to view cars",()=>{
  render(<App/>)
  const findButton=screen.getByRole("button",{name:"Purchase Cars"});
  fireEvent.click(findButton);
  expect(screen.queryByText("HI")).toBeInTheDocument();
})

it("after 5 appointments, purchase button is hidden and correct total commission displayed",()=>{
  render(<App/>)

  for(let i=0; i<5; i++) {
    const findPurchaseButton = screen.getByRole("button", {name: "Purchase Cars"})
    fireEvent.click(findPurchaseButton)
    const findPassButton = screen.getByRole("button", {name: "Pass"})
    fireEvent.click(findPassButton)
  }

  expect(screen.queryByText("Purchase Cars")).not.toBeInTheDocument();
  expect(screen.getByText("$")).toBeInTheDocument();
})

})