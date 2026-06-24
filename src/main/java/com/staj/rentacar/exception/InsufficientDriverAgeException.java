package com.staj.rentacar.exception;

public class InsufficientDriverAgeException extends RentalBusinessException{
    public InsufficientDriverAgeException(int customerAge,int minimumRentalAge) {super("Customer age is not sufficient for this vehicle. Customer age: "
            + customerAge + ", minimum rental age: " + minimumRentalAge);}
}
