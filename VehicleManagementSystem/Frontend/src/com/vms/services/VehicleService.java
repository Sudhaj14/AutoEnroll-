package com.vms.services;

import com.vms.models.Vehicle;
import com.vms.exceptions.VehicleException;
public interface VehicleService {

	void registerVehicle(Vehicle vehicle) throws VehicleException;
	
	void deregisterVehicle(String VIN) throws VehicleException;
	
	Vehicle findVehicle(String VIN) throws VehicleException;

	void updateVehicleStatus(String VIN,String status) throws VehicleException;

}
