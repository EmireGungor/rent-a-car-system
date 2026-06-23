package com.staj.rentacar;

import com.staj.rentacar.dto.RentalResult;
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

        System.out.println("Araç eklendi mi?");
        System.out.println(added);

        Vehicle duplicateCar = new Car("34ABC123", "Honda", "Civic", 1800, true);

        boolean duplicateAdded = rentalService.addVehicle(duplicateCar);

        System.out.println("Aynı plaka ile ikinci araç eklenemiyor mu?");
        System.out.println(duplicateAdded == false);

        Vehicle foundVehicle = rentalService.findVehicleByPlate("34ABC123");

        System.out.println("Araç plakaya göre bulundu mu?");
        System.out.println(foundVehicle != null);

        if (foundVehicle != null) {
            System.out.println("Bulunan araç plakası:");
            System.out.println(foundVehicle.getPlate());

            System.out.println("Kiralama öncesi durum:");
            System.out.println(foundVehicle.getStatus());
        }

        RentalResult rentalResult = rentalService.rentVehicle("34ABC123", 25, 4);

        System.out.println("Araç kiralanabildi mi?");
        System.out.println(rentalResult != null);

        if (rentalResult != null) {
            System.out.println("RentalResult plaka doğru mu?");
            System.out.println(rentalResult.getPlate().equals("34ABC123"));

            System.out.println("RentalResult gün sayısı doğru mu?");
            System.out.println(rentalResult.getDayCount() == 4);

            System.out.println("RentalResult toplam bedel:");
            System.out.println(rentalResult.getTotalPrice());

            System.out.println("Toplam bedel doğru mu?");
            System.out.println(rentalResult.getTotalPrice() == 6000.0);
        }

        System.out.println("Kiralama sonrası durum RENTED mı?");
        System.out.println(car.getStatus());

        RentalResult secondRentalResult = rentalService.rentVehicle("34ABC123", 25, 3);

        System.out.println("Aynı araç tekrar kiralanmaya çalışınca sonuç null mı?");
        System.out.println(secondRentalResult == null);

        boolean returned = rentalService.returnVehicle("34ABC123");

        System.out.println("Araç iade edilebildi mi?");
        System.out.println(returned);

        System.out.println("İade sonrası durum AVAILABLE mı?");
        System.out.println(car.getStatus());

        System.out.println("Kirada olmayan araç tekrar iade edilmeye çalışınca false mu?");
        boolean secondReturnResult = rentalService.returnVehicle("34ABC123");
        System.out.println(secondReturnResult == false);

        System.out.println("Olmayan plaka kiralanmaya çalışınca sonuç null mı?");
        RentalResult notFoundResult = rentalService.rentVehicle("06XYZ999", 25, 3);
        System.out.println(notFoundResult == null);

        System.out.println("Yaşı yetmeyen müşteri araba kiralayınca sonuç null mı?");
        RentalResult underAgeResult = rentalService.rentVehicle("34ABC123", 18, 3);
        System.out.println(underAgeResult == null);

        System.out.println("Yaşı yetmeyen denemeden sonra araç durumu hala AVAILABLE mı?");
        System.out.println(car.getStatus());

        System.out.println("Gün sayısı 0 olunca sonuç null mı?");
        RentalResult zeroDayResult = rentalService.rentVehicle("34ABC123", 25, 0);
        System.out.println(zeroDayResult == null);

        System.out.println("Gün sayısı negatif olunca sonuç null mı?");
        RentalResult negativeDayResult = rentalService.rentVehicle("34ABC123", 25, -2);
        System.out.println(negativeDayResult == null);

        System.out.println("Tüm başarısız denemelerden sonra araç durumu hala AVAILABLE mı?");
        System.out.println(car.getStatus());
    }
}