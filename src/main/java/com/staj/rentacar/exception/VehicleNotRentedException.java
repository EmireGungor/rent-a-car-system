package com.staj.rentacar.exception;

public class VehicleNotRentedException extends RentalBusinessException{
    public VehicleNotRentedException(String plate) {super("Vehicle is not currently rented: " + plate);}
}
