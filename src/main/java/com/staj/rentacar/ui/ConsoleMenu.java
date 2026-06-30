package com.staj.rentacar.ui;

import com.staj.rentacar.model.Car;
import com.staj.rentacar.model.Motorcycle;
import com.staj.rentacar.model.Vehicle;
import com.staj.rentacar.persistence.JsonDataManager;
import com.staj.rentacar.service.RentalService;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {

    private final RentalService rentalService;
    private final JsonDataManager jsonDataManager;
    private final List<Vehicle> vehicles;
    private final Scanner scanner;

    public ConsoleMenu(RentalService rentalService,
                       JsonDataManager jsonDataManager,
                       List<Vehicle> vehicles) {
        this.rentalService = rentalService;
        this.jsonDataManager = jsonDataManager;
        this.vehicles = vehicles;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            printMenu();

            String choice = readChoice("Your choice: ", "1", "2", "3", "4", "5");

            switch (choice) {
                case "1":
                    listVehicles();
                    break;
                case "2":
                case "3":
                case "4":
                    System.out.println("This feature is not implemented yet.");
                    break;
                case "5":
                    saveAndExit();
                    running = false;
                    break;
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("===== Rent A Car Management Panel =====");
        System.out.println("1 - List all vehicles");
        System.out.println("2 - Add new vehicle");
        System.out.println("3 - Rent vehicle");
        System.out.println("4 - Return vehicle");
        System.out.println("5 - Save and exit");
    }

    private String readChoice(String prompt, String... validChoices) {
        while (true) {
            System.out.print(prompt);

            String choice = scanner.nextLine().trim();

            for (String validChoice : validChoices) {
                if (choice.equals(validChoice)) {
                    return choice;
                }
            }

            System.out.println("Invalid choice. Please try again.");
        }
    }

    private void listVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
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

    private void saveAndExit() {
        jsonDataManager.saveVehicles(vehicles);
        System.out.println("Data saved. Exiting application.");
    }
}