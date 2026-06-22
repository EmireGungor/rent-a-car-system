package com.staj.rentacar.model;

import com.staj.rentacar.enums.VehicleStatus;

public abstract class Vehicle {
    private final String plate;
    private final String brand;
    private final String model;
    private final double dailyRentalPrice;
    private VehicleStatus status;

    //constructor
    public Vehicle(String plate, String brand, String model, double dailyRentalPrice) {
        this.plate = plate;
        this.brand = brand;
        this.model = model;
        this.dailyRentalPrice = dailyRentalPrice;
        this.status = VehicleStatus.AVAILABLE; //available default
    }

    //getter (there is no setter because of final)
     public String getPlate() {
        return plate;
    }

    public  String getBrand() {
        return  brand;
    }

    public String getModel() {
        return model;
    }

    public double getDailyRentalPrice() {
        return dailyRentalPrice;
    }

    public  VehicleStatus getStatus() {
        return status;
    }

    public void markAsRented() {
        this.status = VehicleStatus.RENTED; //when the vehicle is rented
    }

    public void markAsAvailable() {
        this.status = VehicleStatus.AVAILABLE; //when the company takes the vehicle back
    }

    //methods
    public abstract int getMinimumRentalAge(); //abstract method for polymorphism

    public double calculateRentalPrice(int dayCount) {
        return dailyRentalPrice * dayCount;
    }


}