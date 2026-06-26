package com.staj.rentacar;

import com.staj.rentacar.exception.RentalBusinessException;
import com.staj.rentacar.model.Car;
import com.staj.rentacar.model.Motorcycle;
import com.staj.rentacar.model.Vehicle;
import com.staj.rentacar.persistence.JsonDataManager;
import com.staj.rentacar.service.RentalService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        JsonDataManager jsonDataManager = new JsonDataManager("data/vehicles.json");

        List<Vehicle> vehicles = jsonDataManager.loadVehicles();

        System.out.println("Loaded vehicle count: " + vehicles.size());
        System.out.println("--------------------");

        printVehicles(vehicles);

        RentalService rentalService = new RentalService(vehicles);

        if (!vehicles.isEmpty()) {
            String firstPlate = vehicles.get(0).getPlate();
            printVehicleByPlate(rentalService, firstPlate);
        }
    }

    private static void printVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles loaded from JSON file.");
            return;
        }

        for (Vehicle vehicle : vehicles) {
            System.out.println("Type: " + vehicle.getClass().getSimpleName());
            System.out.println("Plate: " + vehicle.getPlate());
            System.out.println("Brand: " + vehicle.getBrand());
            System.out.println("Model: " + vehicle.getModel());
            System.out.println("Daily rental price: " + vehicle.getDailyRentalPrice());
            System.out.println("Minimum rental age: " + vehicle.getMinimumRentalAge());
            System.out.println("Status: " + vehicle.getStatus());

            if (vehicle instanceof Car car) {
                System.out.println("Has air conditioning: " + car.hasAirConditioning());
            } else if (vehicle instanceof Motorcycle motorcycle) {
                System.out.println("Helmet required: " + motorcycle.isHelmetRequired());
            }

            System.out.println("--------------------");
        }
    }

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
}