package com.vms.models;

public class Truck extends Vehicle {
    
    private double loadCapacity; 
    
    
    public Truck(String vin, String licensePlate, String make, String model, int year, double loadCapacity) {
        super(vin, licensePlate, make, model, year); 
        this.loadCapacity = loadCapacity;
    }

   
    public double getLoadCapacity() {
        return loadCapacity;
    }

    
    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    
    @Override
    public void register() {
        
        System.out.println("Registering truck with VIN: " + getVIN() + " and License Plate: " + getLicensePlate());
        
        super.register();
    }

    
    @Override
    public void deregister() {
       
        System.out.println("Deregistering truck with VIN: " + getVIN());
        
        super.deregister();
    }

    
    public double calculateTotalWeight(double loadWeight) {
        
        double totalWeight = 5000 + loadWeight;
        return totalWeight;
    }

    
    @Override
    public String toString() {
        return "Truck [VIN=" + getVIN() + ", License Plate=" + getLicensePlate() + 
                ", Make=" + getMake() + ", Model=" + getModel() + ", Year=" + getYear() + 
                ", Load Capacity=" + loadCapacity + " tons]";
    }
}
