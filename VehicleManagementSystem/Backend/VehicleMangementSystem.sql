create database VehicleManagementSystem;
use VehicleManagementSystem;
CREATE TABLE Vehicles (
    VIN VARCHAR(17) PRIMARY KEY, 
    license_plate VARCHAR(15) UNIQUE NOT NULL,
    make VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    year INT NOT NULL,
    registration_date DATE,
    assigned_driver_id INT,
    status VARCHAR(20),
    FOREIGN KEY (assigned_driver_id) REFERENCES Drivers(driver_id)
);


CREATE TABLE Drivers (
    driver_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    license_number VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE VehicleRegistrations (
    registration_id INT PRIMARY KEY AUTO_INCREMENT,
    VIN VARCHAR(17) NOT NULL,
    registration_date DATE NOT NULL,
    action VARCHAR(20),
    FOREIGN KEY (VIN) REFERENCES Vehicles(VIN)
);

CREATE TABLE Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(20) 
);
select * from Vehicles;

ALTER TABLE Vehicles
modify COLUMN registration_date DATETIME DEFAULT CURRENT_TIMESTAMP;

-- Create an index on the VIN column in the Vehicles table
CREATE INDEX idx_vehicle_vin ON Vehicles(VIN);

-- Create an index on the license_plate column in the Vehicles table
CREATE INDEX idx_vehicle_license_plate ON Vehicles(license_plate);

show index from vehicles;
SELECT 
    v.VIN,
    v.license_plate,
    v.make,
    v.model,
    v.year,
    d.driver_id,
    d.first_name,
    d.last_name,
    d.license_number
FROM 
    Vehicles v
JOIN 
    Drivers d ON v.driver_id = d.driver_id;  -- Assuming `driver_id` is the foreign key in the `Vehicles` table

ALTER TABLE vehicles
ADD COLUMN driver_id INT,
ADD CONSTRAINT fk_driver
FOREIGN KEY (driver_id) REFERENCES drivers(driver_id);

CREATE VIEW ActiveVehicles AS
SELECT 
    v.VIN,
    v.license_plate,
    v.make,
    v.model,
    v.year,
    v.driver_id,
    d.first_name
FROM 
    vehicles v
JOIN 
    drivers d ON v.driver_id = d.driver_id
WHERE 
    v.status = 'active';
select * from ActiveVehicles;