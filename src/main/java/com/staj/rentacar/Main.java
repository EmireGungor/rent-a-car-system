package com.staj.rentacar;

import com.staj.rentacar.dto.RentalResult;
import com.staj.rentacar.exception.RentalBusinessException;
import com.staj.rentacar.model.Car;
import com.staj.rentacar.model.Vehicle;
import com.staj.rentacar.service.RentalService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Vehicle> vehicles = new ArrayList<>();
        RentalService rentalService = new RentalService(vehicles);

        Vehicle car = new Car("34ABC123", "Toyota", "Corolla", 1500, true);

        boolean added = rentalService.addVehicle(car);
        System.out.println("Was the vehicle added?");
        System.out.println(added);

        Vehicle duplicateCar = new Car("34ABC123", "Honda", "Civic", 1800, true);

        boolean duplicateAdded = rentalService.addVehicle(duplicateCar);
        System.out.println("Was the duplicate vehicle rejected?");
        System.out.println(!duplicateAdded);

        printVehicleByPlate(rentalService, "34ABC123");

        tryRentVehicle(rentalService, "34ABC123", 25, 4);

        System.out.println("Vehicle status after rental:");
        System.out.println(car.getStatus());

        tryRentVehicle(rentalService, "34ABC123", 25, 3);

        tryReturnVehicle(rentalService, "34ABC123");

        System.out.println("Vehicle status after return:");
        System.out.println(car.getStatus());

        tryReturnVehicle(rentalService, "34ABC123");

        tryRentVehicle(rentalService, "06XYZ999", 25, 3);

        tryRentVehicle(rentalService, "34ABC123", 18, 3);

        System.out.println("Vehicle status after underage rental attempt:");
        System.out.println(car.getStatus());

        tryRentVehicle(rentalService, "34ABC123", 25, 0);

        tryRentVehicle(rentalService, "34ABC123", 25, -2);

        System.out.println("Vehicle status after all failed attempts:");
        System.out.println(car.getStatus());
    }

    //HELPER METHODS
    private static void printVehicleByPlate(RentalService rentalService, String plate) {
        try {
            Vehicle vehicle = rentalService.findVehicleByPlate(plate);

            System.out.println("Vehicle found by plate.");
            System.out.println("Plate: " + vehicle.getPlate());
            System.out.println("Brand: " + vehicle.getBrand());
            System.out.println("Model: " + vehicle.getModel());
            System.out.println("Status: " + vehicle.getStatus());

        } catch (RentalBusinessException exception) {
            System.out.println("Vehicle search failed: " + exception.getMessage());
        }
    }

    private static void tryRentVehicle(RentalService rentalService, String plate, int customerAge, int dayCount) {
        try {
            RentalResult result = rentalService.rentVehicle(plate, customerAge, dayCount);

            System.out.println("Rental successful.");
            System.out.println("Plate: " + result.getPlate());
            System.out.println("Day count: " + result.getDayCount());
            System.out.println("Total price: " + result.getTotalPrice());

        } catch (RentalBusinessException exception) {
            System.out.println("Rental failed: " + exception.getMessage());
        }
    }

    private static void tryReturnVehicle(RentalService rentalService, String plate) {
        try {
            boolean returned = rentalService.returnVehicle(plate);

            System.out.println("Vehicle return successful.");
            System.out.println("Plate: " + plate);
            System.out.println("Returned: " + returned);

        } catch (RentalBusinessException exception) {
            System.out.println("Vehicle return failed: " + exception.getMessage());
        }
    }
}