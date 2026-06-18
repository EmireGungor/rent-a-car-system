package com.staj.rentacar.model;

//inheritance
public class Motorcycle extends Vehicle {
    private final boolean helmetRequired;

    public Motorcycle(String plate, String brand, String model, double dailyRentalPrice, boolean helmetRequired) {
        super(plate, brand, model, dailyRentalPrice);
        this.helmetRequired = helmetRequired;
    }

    //getter
    public boolean isHelmetRequired() {
        return helmetRequired;
    }

    @Override
    public int getMinimumRentalAge() {
        return 18;
    }
}
