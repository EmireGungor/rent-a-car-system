package com.staj.rentacar.exception;

public class InvalidRentalDurationException extends RentalBusinessException{
    public InvalidRentalDurationException(int dayCount) {super("Rental duration must be greater than zero. Day count: " + dayCount);}
}
