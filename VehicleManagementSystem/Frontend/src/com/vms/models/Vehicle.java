package com.vms.models;
import java.sql.Timestamp;
public class Vehicle {
    
    private String VIN;
    private String licensePlate;
    private String make;
    private String model;
    private int year;
    private String status; 
    private Timestamp registrationDate;

    
    public Vehicle(String VIN, String licensePlate, String make, String model, int year) {
        this.VIN = VIN;
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
        this.year = year;
    }

  
    public Vehicle(String VIN, String licensePlate, String make, String model, int year, String status) {
        this.VIN = VIN;
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
        this.year = year;
        this.status = status; 
        this.registrationDate = new Timestamp(System.currentTimeMillis()); 
    }

    
    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        if (VIN != null && !VIN.isEmpty()) {
            this.VIN = VIN;
        } else {
            throw new IllegalArgumentException("VIN cannot be null or empty");
        }
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        if (licensePlate != null && !licensePlate.isEmpty()) {
            this.licensePlate = licensePlate;
        } else {
            throw new IllegalArgumentException("License plate cannot be null or empty");
        }
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        if (make != null && !make.isEmpty()) {
            this.make = make;
        } else {
            throw new IllegalArgumentException("Make cannot be null or empty");
        }
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (model != null && !model.isEmpty()) {
            this.model = model;
        } else {
            throw new IllegalArgumentException("Model cannot be null or empty");
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year > 1885 && year <= 2024) {
            this.year = year;
        } else {
            throw new IllegalArgumentException("Invalid year for a vehicle");
        }
    }

    public String getStatus() {
        return status;  
    }

    public void setStatus(String status) {
        if (status != null && !status.isEmpty()) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
    }

    public void register() {
        System.out.println("Registering vehicle: " + VIN);
    }

    public void deregister() {
        System.out.println("Deregistering vehicle: " + VIN);
    }

    @Override
    public String toString() {
        return "Vehicle [VIN=" + VIN + ", License Plate=" + licensePlate +
                ", Make=" + make + ", Model=" + model + ", Year=" + year + ", Status=" + status + "]";
    }
}
