package com.staj.rentacar;

import com.staj.rentacar.model.Vehicle;
import com.staj.rentacar.persistence.JsonDataManager;
import com.staj.rentacar.service.RentalService;
import com.staj.rentacar.ui.ConsoleMenu;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        JsonDataManager jsonDataManager = new JsonDataManager("data/vehicles.json");

        List<Vehicle> vehicles = jsonDataManager.loadVehicles();

        RentalService rentalService = new RentalService(vehicles);

        ConsoleMenu consoleMenu = new ConsoleMenu(rentalService, jsonDataManager, vehicles);

        consoleMenu.start();
    }
}