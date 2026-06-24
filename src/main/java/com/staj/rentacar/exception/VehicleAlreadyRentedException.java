package com.staj.rentacar.exception;

public class VehicleAlreadyRentedException extends RentalBusinessException{
    public VehicleAlreadyRentedException(String plate){super("Vehicle is already rented: " + plate);}
}
