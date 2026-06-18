package com.staj.rentacar;
import com.staj.rentacar.model.Vehicle; //for TEST2
import com.staj.rentacar.model.Car;
import com.staj.rentacar.model.Motorcycle;

public class Main {
    public static void main(String[] args) {
        //TEST1 -------------------
        /*
        Car car = new Car("34ABC123", "Toyota", "Corolla", 1500.0, true);
        Motorcycle motorcycle = new Motorcycle("35MTR456", "Yamaha", "MT-07", 900.0, true);

        System.out.println("Car plate: " + car.getPlate());
        System.out.println("Car brand: " + car.getBrand());
        System.out.println("Car model: " + car.getModel());
        System.out.println("Car minimum rental age: " + car.getMinimumRentalAge());
        System.out.println("Car has air conditioning: " + car.hasAirConditioning());
        System.out.println("Car status: " + car.getStatus());
        System.out.println("Car rental price for 3 days: " + car.calculateRentalPrice(3));

        car.markAsRented();

        System.out.println("Car status after renting: " + car.getStatus());

        System.out.println("--------------------");

        System.out.println("Motorcycle plate: " + motorcycle.getPlate());
        System.out.println("Motorcycle brand: " + motorcycle.getBrand());
        System.out.println("Motorcycle model: " + motorcycle.getModel());
        System.out.println("Motorcycle minimum rental age: " + motorcycle.getMinimumRentalAge());
        System.out.println("Motorcycle helmet required: " + motorcycle.isHelmetRequired());
        System.out.println("Motorcycle status: " + motorcycle.getStatus());
        System.out.println("Motorcycle rental price for 2 days: " + motorcycle.calculateRentalPrice(2));

        motorcycle.markAsRented();

        System.out.println("Motorcycle status after renting: " + motorcycle.getStatus());
        */

        //TEST2--------------------------------------
        //Bu testte hasAirConditioning() veya isHelmetRequired() çağırmıyoruz. Çünkü vehicle değişkeninin tipi Vehicle. Vehicle, araba veya motorun özel detaylarını bilmez.
        //Ortak davranışlara Vehicle üzerinden erişirsin.
        //Türe özel davranışlara ancak o tür üzerinden erişirsin.
        Vehicle[] vehicles = {
                new Car("34CAR001", "Honda", "Civic", 1700.0, true),
                new Motorcycle("35MOTO001", "Yamaha", "R25", 800.0, true)
        };

        for (Vehicle vehicle : vehicles) {
            System.out.println("Plate: " + vehicle.getPlate());
            System.out.println("Minimum rental age: " + vehicle.getMinimumRentalAge());
            System.out.println("Rental price for 2 days: " + vehicle.calculateRentalPrice(2));
            System.out.println("Status: " + vehicle.getStatus());
            System.out.println("--------------------");
        }
    }
}