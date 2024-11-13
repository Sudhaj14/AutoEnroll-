package com.vms;

import com.vms.models.Car;
import com.vms.models.Truck;
import com.vms.models.MotorCycle;
import com.vms.services.VehicleServicesImpl;
import com.vms.exceptions.VehicleException;
import com.vms.models.Vehicle;
import java.util.Scanner;

public class Main {

    private VehicleServicesImpl vehicleService;

    
    public Main() {
        vehicleService = new VehicleServicesImpl();
    }

    
    public void start() {
        Scanner scanner = new Scanner(System.in);

       
        System.out.println("Welcome to the Vehicle Management System!");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Register a Vehicle");
            System.out.println("2. Find a Vehicle");
            System.out.println("3. Update Vehicle Status");
            System.out.println("4. Deregister a Vehicle");
            System.out.println("5. Exit");

            
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    
                    System.out.println("Enter the type of vehicle (Car, Truck, MotorCycle): ");
                    String vehicleType = scanner.nextLine();
                    System.out.println("Enter VIN: ");
                    String vin = scanner.nextLine();
                    System.out.println("Enter License Plate: ");
                    String licensePlate = scanner.nextLine();
                    System.out.println("Enter Make: ");
                    String make = scanner.nextLine();
                    System.out.println("Enter Model: ");
                    String model = scanner.nextLine();
                    System.out.println("Enter Year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.println("Enter other attributes based on vehicle type:");

                    if (vehicleType.equalsIgnoreCase("Car")) {
                        System.out.println("Enter number of doors: ");
                        int doors = scanner.nextInt();
                        System.out.println("Is it a convertible (true/false)? ");
                        boolean isConvertible = scanner.nextBoolean();
                        scanner.nextLine(); 

                        
                        Car car = new Car(vin, licensePlate, make, model, year, doors, isConvertible);
                        try {
                            vehicleService.registerVehicle(car);
                        } catch (VehicleException e) {
                            System.out.println("Error: " + e.getMessage());
                        }

                    } else if (vehicleType.equalsIgnoreCase("Truck")) {
                        System.out.println("Enter cargo capacity: ");
                        int cargoCapacity = scanner.nextInt();
                        scanner.nextLine(); 

                       
                        Truck truck = new Truck(vin, licensePlate, make, model, year, cargoCapacity);
                        try {
                            vehicleService.registerVehicle(truck);
                        } catch (VehicleException e) {
                            System.out.println("Error: " + e.getMessage());
                        }

                    } else if (vehicleType.equalsIgnoreCase("MotorCycle")) {
                        System.out.println("Is it a sports bike (true/false)? ");
                        boolean isSportBike = scanner.nextBoolean();
                        scanner.nextLine(); 

                        
                        MotorCycle motorCycle = new MotorCycle(vin, licensePlate, make, model, year, isSportBike);
                        try {
                            vehicleService.registerVehicle(motorCycle);
                        } catch (VehicleException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Invalid vehicle type entered.");
                    }
                    break;

                case 2:
                    
                    System.out.println("Enter VIN to find a vehicle: ");
                    String findVin = scanner.nextLine();
                    try {
                        Vehicle vehicle = vehicleService.findVehicle(findVin);
                        System.out.println("Vehicle Found: " + vehicle.getMake() + " " + vehicle.getModel());
                    } catch (VehicleException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    
                    System.out.println("Enter VIN to update status: ");
                    String updateVin = scanner.nextLine();
                    System.out.println("Enter new status: ");
                    String status = scanner.nextLine();
                    try {
                        vehicleService.updateVehicleStatus(updateVin, status);
                    } catch (VehicleException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                  
                    System.out.println("Enter VIN to deregister the vehicle: ");
                    String deregisterVin = scanner.nextLine();
                    try {
                        vehicleService.deregisterVehicle(deregisterVin);
                    } catch (VehicleException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 5:
                    
                    System.out.println("Exiting Vehicle Management System. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        // Create an instance of Main and start the process
        Main main = new Main();
        main.start();
    }
}
