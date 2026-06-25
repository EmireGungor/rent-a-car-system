package com.staj.rentacar.exception;

public class InvalidVehicleException extends RentalBusinessException {
    public InvalidVehicleException() {
        super("Vehicle cannot be null");
    }
}