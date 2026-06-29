package com.staj.rentacar;

import com.staj.rentacar.dto.RentalResult;
import com.staj.rentacar.exception.DuplicateVehiclePlateException;
import com.staj.rentacar.exception.InsufficientDriverAgeException;
import com.staj.rentacar.exception.InvalidRentalDurationException;
import com.staj.rentacar.exception.InvalidVehicleException;
import com.staj.rentacar.exception.VehicleAlreadyRentedException;
import com.staj.rentacar.exception.VehicleNotFoundException;
import com.staj.rentacar.exception.VehicleNotRentedException;
import com.staj.rentacar.model.Car;
import com.staj.rentacar.model.Motorcycle;
import com.staj.rentacar.model.Vehicle;
import com.staj.rentacar.persistence.JsonDataManager;
import com.staj.rentacar.service.RentalService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JsonDataManager jsonDataManager = new JsonDataManager("data/vehicles.json");

        List<Vehicle> testVehicles = createTestVehicles();
        jsonDataManager.saveVehicles(testVehicles);

        List<Vehicle> vehicles = jsonDataManager.loadVehicles();
        RentalService rentalService = new RentalService(vehicles);

        printVehicles(vehicles);

        printVehicleByPlate(rentalService, "34ABC123");
        printVehicleByPlate(rentalService, "35MOTO001");
        printVehicleByPlate(rentalService, "06YOK001");

        tryRentVehicle(rentalService, "34ABC123", 25, 3);
        tryReturnVehicle(rentalService, "34ABC123");
        tryReturnVehicle(rentalService, "34ABC123");

        tryRentVehicle(rentalService, "35MOTO001", 20, 2);
        tryRentVehicle(rentalService, "34ABC123", 18, 2);
        tryRentVehicle(rentalService, "34ABC123", 25, 0);

        jsonDataManager.saveVehicles(vehicles);

        List<Vehicle> reloadedVehicles = jsonDataManager.loadVehicles();

        System.out.println("Final vehicle list after save and reload:");
        System.out.println("--------------------");
        printVehicles(reloadedVehicles);
    }

    private static List<Vehicle> createTestVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        RentalService rentalService = new RentalService(vehicles);

        Vehicle car = new Car("34ABC123", "Toyota", "Corolla", 1500.0, true);
        Vehicle motorcycle = new Motorcycle("35MOTO001", "Yamaha", "R25", 800.0, true);
        Vehicle duplicateCar = new Car("34ABC123", "Honda", "Civic", 1800.0, true);

        car.markAsRented();

        tryAddVehicle(rentalService, null);
        tryAddVehicle(rentalService, car);
        tryAddVehicle(rentalService, motorcycle);
        tryAddVehicle(rentalService, duplicateCar);

        return vehicles;
    }

    private static void tryAddVehicle(RentalService rentalService, Vehicle vehicle) {
        try {
            rentalService.addVehicle(vehicle);

            System.out.println("Vehicle added successfully.");
            System.out.println("Plate: " + vehicle.getPlate());
            System.out.println("--------------------");

        } catch (InvalidVehicleException exception) {
            System.out.println("Vehicle add failed because vehicle is invalid.");
            System.out.println(exception.getMessage());
            System.out.println("--------------------");

        } catch (DuplicateVehiclePlateException exception) {
            System.out.println("Vehicle add failed because plate already exists.");
            System.out.println(exception.getMessage());
            System.out.println("--------------------");
        }
    }

    private static void printVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles loaded from JSON file.");
            System.out.println("--------------------");
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
            System.out.println("--------------------");

        } catch (VehicleNotFoundException exception) {
            System.out.println("Vehicle search failed because vehicle was not found.");
            System.out.println(exception.getMessage());
            System.out.println("--------------------");
        }
    }

    private static void tryRentVehicle(RentalService rentalService, String plate, int customerAge, int dayCount) {
        try {
            RentalResult result = rentalService.rentVehicle(plate, customerAge, dayCount);

            System.out.println("Rental successful.");
            System.out.println("Plate: " + result.getPlate());
            System.out.println("Day count: " + result.getDayCount());
            System.out.println("Total price: " + result.getTotalPrice());
            System.out.println("--------------------");

        } catch (InvalidRentalDurationException exception) {
            System.out.println("Rental failed because rental duration is invalid.");
            System.out.println(exception.getMessage());
            System.out.println("--------------------");

        } catch (VehicleNotFoundException exception) {
            System.out.println("Rental failed because vehicle was not found.");
            System.out.println(exception.getMessage());
            System.out.println("--------------------");

        } catch (VehicleAlreadyRentedException exception) {
            System.out.println("Rental failed because vehicle is already rented.");
            System.out.println(exception.getMessage());
            System.out.println("--------------------");

        } catch (InsufficientDriverAgeException exception) {
            System.out.println("Rental failed because customer age is not sufficient.");
            System.out.println(exception.getMessage());
            System.out.println("--------------------");
        }
    }

    private static void tryReturnVehicle(RentalService rentalService, String plate) {
        try {
            rentalService.returnVehicle(plate);

            System.out.println("Vehicle return successful.");
            System.out.println("Plate: " + plate);
            System.out.println("--------------------");

        } catch (VehicleNotFoundException exception) {
            System.out.println("Vehicle return failed because vehicle was not found.");
            System.out.println(exception.getMessage());
            System.out.println("--------------------");

        } catch (VehicleNotRentedException exception) {
            System.out.println("Vehicle return failed because vehicle is not currently rented.");
            System.out.println(exception.getMessage());
            System.out.println("--------------------");
        }
    }
}