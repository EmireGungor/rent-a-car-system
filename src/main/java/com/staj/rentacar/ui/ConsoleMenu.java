package com.staj.rentacar.ui;

import com.staj.rentacar.exception.RentalBusinessException;
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
                    addNewVehicle();
                    break;
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

    // HELPER METHODS
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

    private String readRequiredText(String prompt) {
        while (true) {
            System.out.print(prompt);

            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("This field cannot be empty. Please try again.");
        }
    }

    private double readPositiveDouble(String prompt) {
        while (true) {
            System.out.print(prompt);

            String input = scanner.nextLine().trim();

            try {
                double value = Double.parseDouble(input);

                if (value > 0) {
                    return value;
                }

                System.out.println("Value must be greater than 0. Example: 1500.50.");
            } catch (NumberFormatException exception) {
                System.out.println("Invalid number. Use a positive number like 1500.50.");
            }
        }
    }

    private boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);

            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("y") || input.equals("yes")) {
                return true;
            }

            if (input.equals("n") || input.equals("no")) {
                return false;
            }

            System.out.println("Invalid choice. Please enter y/yes or n/no.");
        }
    }

    //CASES
    private void listVehicles() { //RUN WITH CASE 1
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

    private void addNewVehicle() { // RUN WITH CASE 2
        try {
            String vehicleType = readChoice("Vehicle type (1-Car, 2-Motorcycle): ", "1", "2");

            String plate = readRequiredText("Plate: ");
            String brand = readRequiredText("Brand: ");
            String model = readRequiredText("Model: ");
            double dailyRentalPrice = readPositiveDouble("Daily rental price: ");

            Vehicle vehicle;

            if (vehicleType.equals("1")) {
                boolean hasAirConditioning = readYesNo("Has air conditioning? (y/n): ");
                vehicle = new Car(plate, brand, model, dailyRentalPrice, hasAirConditioning);
            } else {
                boolean helmetRequired = readYesNo("Helmet required? (y/n): ");
                vehicle = new Motorcycle(plate, brand, model, dailyRentalPrice, helmetRequired);
            }

            rentalService.addVehicle(vehicle);
            System.out.println("Vehicle added successfully.");
        } catch (RentalBusinessException exception) {
            System.out.println("Vehicle could not be added: " + exception.getMessage());
        }
    }

    private void saveAndExit() { // RUN WITH CASE 5
        jsonDataManager.saveVehicles(vehicles);
        System.out.println("Data saved. Exiting application.");
    }
}