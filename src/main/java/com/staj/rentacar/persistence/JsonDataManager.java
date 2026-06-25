package com.staj.rentacar.persistence;

import com.staj.rentacar.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class JsonDataManager {

    private final String filePath;

    public JsonDataManager(String filePath) {
        this.filePath = filePath;
    }

    public List<Vehicle> loadVehicles() {
        // JSON dosyasından araçları okuyacak
        // TODO
        /*
        eğer dosya yoksa:
          klasörü oluştur
          dosyayı oluştur
          içine [] yaz
          boş liste döndür

        dosya varsa:
          JSON içeriğini oku
          eğer boşsa veya [] ise:
            boş liste döndür

          JSON array içindeki her aracı oku
          type alanına göre Car veya Motorcycle oluştur
          listeye ekle

          listeyi döndür
        */

        return new ArrayList<>();
    }

    public void saveVehicles(List<Vehicle> vehicles) {
        // Güncel araç listesini JSON dosyasına yazacak
        // TODO
    }
}