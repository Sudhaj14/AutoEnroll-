package com.vms.models;

public class MotorCycle extends Vehicle {
    
    private boolean hasSidecar; 
    
   
    public MotorCycle(String vin, String licensePlate, String make, String model, int year, boolean hasSideCar) {
        super(vin, licensePlate, make, model, year); 
        this.hasSidecar = hasSideCar;
    }

    
    public boolean hasSidecar() {
        return hasSidecar;
    }

 
    public void setHasSidecar(boolean hasSidecar) {
        this.hasSidecar = hasSidecar;
    }

    
    @Override
    public void register() {
        
        System.out.println("Registering motorcycle with VIN: " + getVIN() + " and License Plate: " + getLicensePlate());
        
        super.register();
    }

    
    @Override
    public void deregister() {
        
        System.out.println("Deregistering motorcycle with VIN: " + getVIN());
        
        super.deregister();
    }

   
    public String canCarryPassenger() {
        if (hasSidecar) {
            return "This motorcycle can carry a passenger in the sidecar.";
        } else {
            return "This motorcycle cannot carry a passenger in a sidecar.";
        }
    }

    
    @Override
    public String toString() {
        return "Motorcycle [VIN=" + getVIN() + ", License Plate=" + getLicensePlate() + 
                ", Make=" + getMake() + ", Model=" + getModel() + ", Year=" + getYear() + 
                ", Has Sidecar=" + (hasSidecar ? "Yes" : "No") + "]";
    }
}
