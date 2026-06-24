package com.staj.rentacar.dto;

// What is the result of RentalService
public class RentalResult {
    private final String plate;
    private final int dayCount;
    private final double totalPrice;

    public RentalResult(String plate, int dayCount, double totalPrice) {
        this.plate = plate;
        this.dayCount = dayCount;
        this.totalPrice = totalPrice;
    }

    public String getPlate() { return plate; }

    public int getDayCount() { return dayCount; }

    public double getTotalPrice() {
        return totalPrice;
    }
}