package com.staj.rentacar.dto;

//Kiralama başarılı olduysa sonuç ne?
public class RentalResult {
    private final String plate;
    private final int dayCount;
    private final double totalPrice;

    public RentalResult(String plate, int dayCount, double totalPrice) {
        this.plate = plate;
        this.dayCount = dayCount;
        this.totalPrice = totalPrice;
    }

    public String getPlate() {
        return plate;
    }

    public int getDayCount() {
        return dayCount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}