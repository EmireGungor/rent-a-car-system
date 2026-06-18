package com.staj.rentacar.model;

//inheritance
public class Car extends Vehicle {
    private final boolean hasAirConditioning;

    public Car(String plate, String brand, String model, double dailyRentalPrice, boolean hasAirConditioning) {
        super(plate, brand, model, dailyRentalPrice);
        this.hasAirConditioning = hasAirConditioning;
    }

    //getter
     public boolean hasAirConditioning() {
        return hasAirConditioning;
     }

    @Override
    public int getMinimumRentalAge() {
        return 21;
    }
}

