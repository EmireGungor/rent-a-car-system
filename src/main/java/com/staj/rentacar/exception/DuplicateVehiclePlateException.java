package com.staj.rentacar.exception;

public class DuplicateVehiclePlateException extends RentalBusinessException{
    public DuplicateVehiclePlateException(String plate) {super("Duplicate vehicle plate is not allowed: " + plate);}
}
