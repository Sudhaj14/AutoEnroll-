package com.vms.services;

import com.vms.database.DBConnection;
import com.vms.models.Vehicle;
import com.vms.exceptions.VehicleException;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

public class VehicleServicesImpl implements VehicleService {

    @Override
    public void registerVehicle(Vehicle vehicle) throws VehicleException {
        if (vehicle == null) {
            throw new VehicleException("Vehicle cannot be null");
        }

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); // Start transaction

            // Insert into Vehicles table
            String sqlInsertVehicle = "INSERT INTO Vehicles (vin, license_plate, make, model, year, status) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmtVehicle = conn.prepareStatement(sqlInsertVehicle);
            stmtVehicle.setString(1, vehicle.getVIN());
            stmtVehicle.setString(2, vehicle.getLicensePlate());
            stmtVehicle.setString(3, vehicle.getMake());
            stmtVehicle.setString(4, vehicle.getModel());
            stmtVehicle.setInt(5, vehicle.getYear());
            stmtVehicle.setString(6, "Registered");
            stmtVehicle.executeUpdate();

            // Insert into VehicleRegistrations table
            String sqlInsertRegistration = "INSERT INTO VehicleRegistrations (vin, registration_date, action) VALUES (?, ?, ?)";
            PreparedStatement stmtRegistration = conn.prepareStatement(sqlInsertRegistration);
            stmtRegistration.setString(1, vehicle.getVIN());
            stmtRegistration.setDate(2, new Date(System.currentTimeMillis())); // Current date
            stmtRegistration.setString(3, "Registered");
            stmtRegistration.executeUpdate();

            conn.commit(); // Commit transaction
            System.out.println("Vehicle with VIN " + vehicle.getVIN() + " has been successfully registered.");

        } catch (SQLException e) {
            throw new VehicleException("Error during vehicle registration: " + e.getMessage());
        }
    }

    @Override
    public void deregisterVehicle(String VIN) throws VehicleException {
        if (VIN == null || VIN.isEmpty()) {
            throw new VehicleException("VIN cannot be null or empty");
        }

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); // Start transaction

            // Update status in Vehicles table
            String sqlUpdateVehicle = "UPDATE Vehicles SET status = ? WHERE vin = ?";
            PreparedStatement stmtUpdateVehicle = conn.prepareStatement(sqlUpdateVehicle);
            stmtUpdateVehicle.setString(1, "Deregistered");
            stmtUpdateVehicle.setString(2, VIN);
            int rowsAffected = stmtUpdateVehicle.executeUpdate();

            if (rowsAffected == 0) {
                throw new VehicleException("Vehicle with VIN " + VIN + " not found.");
            }

            // Insert into VehicleRegistrations table
            String sqlInsertDeregistration = "INSERT INTO VehicleRegistrations (vin, registration_date, action) VALUES (?, ?, ?)";
            PreparedStatement stmtInsertDeregistration = conn.prepareStatement(sqlInsertDeregistration);
            stmtInsertDeregistration.setString(1, VIN);
            stmtInsertDeregistration.setDate(2, new Date(System.currentTimeMillis())); // Current date
            stmtInsertDeregistration.setString(3, "Deregistered");
            stmtInsertDeregistration.executeUpdate();

            conn.commit(); // Commit transaction
            System.out.println("Vehicle with VIN " + VIN + " has been successfully deregistered.");

        } catch (SQLException e) {
            throw new VehicleException("Error during vehicle deregistration: " + e.getMessage());
        }
    }
    @Override
    public void updateVehicleStatus(String VIN, String status) throws VehicleException {
        if (VIN == null || VIN.isEmpty() || status == null || status.isEmpty()) {
            throw new VehicleException("VIN and status cannot be null or empty");
        }

        try (Connection conn = DBConnection.getConnection()) {
            String sqlUpdateStatus = "UPDATE Vehicles SET status = ? WHERE vin = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlUpdateStatus);
            stmt.setString(1, status);
            stmt.setString(2, VIN);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new VehicleException("No vehicle found with VIN " + VIN);
            }

            System.out.println("Status of vehicle with VIN " + VIN + " has been updated to: " + status);
        } catch (SQLException e) {
            throw new VehicleException("Error updating vehicle status: " + e.getMessage());
        }
    }
    
    @Override
    public Vehicle findVehicle(String VIN) throws VehicleException {
        try (Connection conn = DBConnection.getConnection()) {
            // SQL query to find vehicle by VIN
            String sqlFindVehicle = "SELECT * FROM Vehicles WHERE vin = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlFindVehicle);
            stmt.setString(1, VIN);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Assuming you have a constructor in Vehicle class that takes VIN, licensePlate, make, model, year, status
                Vehicle vehicle = new Vehicle(
                    rs.getString("vin"),
                    rs.getString("license_plate"),
                    rs.getString("make"),
                    rs.getString("model"),
                    rs.getInt("year"),
                    rs.getString("status")
                );
                return vehicle;
            } else {
                throw new VehicleException("Vehicle with VIN " + VIN + " not found.");
            }
        } catch (SQLException e) {
            throw new VehicleException("Error retrieving vehicle: " + e.getMessage());
        }
    }

}
