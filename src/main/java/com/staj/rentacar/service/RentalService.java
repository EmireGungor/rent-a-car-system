package com.staj.rentacar.service;

import com.staj.rentacar.exception.*;
import com.staj.rentacar.model.Vehicle;
import com.staj.rentacar.dto.RentalResult;
import com.staj.rentacar.enums.VehicleStatus;

import java.util.List;

public class RentalService {

    private final List<Vehicle> vehicles;

    public RentalService(List<Vehicle> vehicles) {
        if (vehicles == null) {
            throw new IllegalArgumentException("Vehicle list cannot be null");
        }

        this.vehicles = vehicles;
    }

    public Vehicle findVehicleByPlate(String plate) {
        if (plate == null) {
            throw new VehicleNotFoundException(plate);
        }

        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPlate().equals(plate)) {
                return vehicle;
            }
        }
        throw new VehicleNotFoundException(plate);
    }

    public boolean addVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            return false;
        }

        for (Vehicle existingVehicle : vehicles) {
            if (existingVehicle.getPlate().equalsIgnoreCase(vehicle.getPlate())) { //ignores upper-lower case
                return false;
            }
        }

        vehicles.add(vehicle);
        return true;
    }

    public RentalResult rentVehicle(String plate, int customerAge, int dayCount) {
        // If the day count is invalid, there is no need to search for the vehicle.
        if (dayCount <= 0) {
            throw new InvalidRentalDurationException(dayCount);
        }

        Vehicle vehicle = findVehicleByPlate(plate);

        if (vehicle.getStatus() == VehicleStatus.RENTED) {
            throw new VehicleAlreadyRentedException(plate);
        }

        if (customerAge < vehicle.getMinimumRentalAge()) {
            throw new InsufficientDriverAgeException(customerAge, vehicle.getMinimumRentalAge());
        }

        double totalPrice = vehicle.calculateRentalPrice(dayCount);
        vehicle.markAsRented();

        return new RentalResult(vehicle.getPlate(), dayCount, totalPrice);
    }

    public boolean returnVehicle(String plate) {
        Vehicle vehicle = findVehicleByPlate(plate);

        if (vehicle.getStatus() == VehicleStatus.AVAILABLE) {
            throw new VehicleNotRentedException(plate);
        }
            vehicle.markAsAvailable();
            return true;
    }
}