package com.staj.rentacar.persistence;

import com.staj.rentacar.model.Vehicle;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//Gson ile içeriği JSON array’e çevirmek için
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import com.google.gson.JsonObject;

import com.staj.rentacar.model.Car;
import com.staj.rentacar.model.Motorcycle;
import com.staj.rentacar.enums.VehicleStatus;

import java.util.ArrayList;
import java.util.List;
public class JsonDataManager {

    private final String filePath;

    public JsonDataManager(String filePath) {
        this.filePath = filePath;
    }

    // Reads vehicles from the JSON file
    public List<Vehicle> loadVehicles() {
        try {
            // Create a Path object from the file path
            Path path = Path.of(filePath);

            // Get the parent folder of the JSON file
            Path parent = path.getParent();

            // If the path has a parent folder, make sure that folder exists
            if (parent != null) {
                Files.createDirectories(parent);
            }

            // If the JSON file does not exist, create it with an empty JSON array
            if (!Files.exists(path)) {
                Files.writeString(path, "[]");
                return new ArrayList<>();
            }

            // Read the JSON file content as text
            String jsonContent = Files.readString(path);

            // If the file is empty or contains only spaces, reset it to an empty JSON array
            if (jsonContent.trim().isEmpty()) {
                Files.writeString(path, "[]");
                return new ArrayList<>();
            }
            // Convert the file content into a JSON element with JsonParser
            JsonElement jsonElement = JsonParser.parseString(jsonContent);

            // Convert the JSON element into a JSON array with getAsJsonArray
            JsonArray jsonArray = jsonElement.getAsJsonArray();

            // Create a list for loaded vehicles
            List<Vehicle> vehicles = new ArrayList<>();

            // If the JSON array has no vehicles, return the empty list
            if (jsonArray.isEmpty()) {
                return vehicles;
            }

            // Read each vehicle object from the JSON array
            for (JsonElement vehicleElement : jsonArray) { //for each
                JsonObject vehicleObject = vehicleElement.getAsJsonObject(); //take records on jsonArray as a JsonObject

                // Read the vehicle type to decide which subclass to create
                String type = vehicleObject.get("type").getAsString();

                // Read common vehicle fields
                String plate = vehicleObject.get("plate").getAsString();
                String brand = vehicleObject.get("brand").getAsString();
                String model = vehicleObject.get("model").getAsString();
                double dailyRentalPrice = vehicleObject.get("dailyRentalPrice").getAsDouble();
                String status = vehicleObject.get("status").getAsString();

                // Convert status text to VehicleStatus enum
                VehicleStatus vehicleStatus = VehicleStatus.valueOf(status);

                // This variable will hold the created vehicle object
                Vehicle vehicle;

                if ("CAR".equalsIgnoreCase(type)) {
                    // Read Car-specific field
                    boolean hasAirConditioning = vehicleObject.get("hasAirConditioning").getAsBoolean();

                    // Create a Car object
                    vehicle = new Car(plate, brand, model, dailyRentalPrice, hasAirConditioning);

                } else if ("MOTORCYCLE".equalsIgnoreCase(type)) {
                    // Read Motorcycle-specific field
                    boolean helmetRequired = vehicleObject.get("helmetRequired").getAsBoolean();

                    // Create a Motorcycle object
                    vehicle = new Motorcycle(plate, brand, model, dailyRentalPrice, helmetRequired);

                } else {
                    // Unknown vehicle type means the JSON data is invalid
                    throw new IllegalStateException("Unknown vehicle type: " + type);
                }

                // Apply the saved vehicle status
                if (vehicleStatus == VehicleStatus.RENTED) {
                    vehicle.markAsRented();
                } else {
                    vehicle.markAsAvailable();
                }

                // Add the created vehicle to the list
                vehicles.add(vehicle);
            }

            // Return the loaded vehicles
            return vehicles;


        } catch (IOException exception) {
            // Convert file reading/writing errors into an unchecked exception
            throw new IllegalStateException("Could not load vehicles from file", exception);
        }
    }

    // Güncel araç listesini JSON dosyasına yazma
    public void saveVehicles(List<Vehicle> vehicles) {

        // TODO
    }
}