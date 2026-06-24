package com.staj.rentacar.exception;

public class VehicleNotFoundException extends RentalBusinessException{
    public VehicleNotFoundException(String plate) {
        super("No vehicle found with plate: " + plate);
    }
}
