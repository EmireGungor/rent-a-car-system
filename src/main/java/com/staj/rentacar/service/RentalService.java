package com.staj.rentacar.service;

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
            return null;
        }

        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPlate().equals(plate)) {
                return vehicle;
            }
        }
        return null;
    }

    public boolean addVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            return false;
        }
        if (findVehicleByPlate(vehicle.getPlate()) != null) { //vehicle exists!!!
            return false;
        }
      vehicles.add(vehicle);
        return true;
    }

    public RentalResult rentVehicle(String plate, int customerAge, int dayCount) {
        Vehicle vehicle = findVehicleByPlate(plate);

        if (vehicle == null) {
            return null;
        }

        if (vehicle.getStatus() == VehicleStatus.RENTED) {
            return null;
        }

        if (customerAge < vehicle.getMinimumRentalAge()) {
            return null;
        }

        if (dayCount <= 0) {
            return null;
        }

        double totalPrice = vehicle.calculateRentalPrice(dayCount);
        vehicle.markAsRented();

        return new RentalResult(vehicle.getPlate(), dayCount, totalPrice);
    }

    public boolean returnVehicle(String plate) {
        Vehicle vehicle = findVehicleByPlate(plate);

        if (vehicle == null) {
            return false;
        }
        if (vehicle.getStatus() == VehicleStatus.AVAILABLE) {
            return false;
        }
        if (vehicle.getStatus() == VehicleStatus.RENTED) {
            vehicle.markAsAvailable();
            return true;
        }
        return false;
    }
}