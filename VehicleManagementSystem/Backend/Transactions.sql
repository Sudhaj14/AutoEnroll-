use vehiclemanagementsystem
DELIMITER //
CREATE PROCEDURE register_vehicle_transaction(
    IN p_VIN VARCHAR(17),
    IN p_license_plate VARCHAR(20),
    IN p_make VARCHAR(50),
    IN p_model VARCHAR(50),
    IN p_year INT,
    IN p_driver_id INT,
    OUT p_result VARCHAR(100)
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SET p_result = 'Error: Registration failed due to an issue.';
    END;

    START TRANSACTION;

    -- Insert into Vehicles table
    INSERT INTO vehicles (VIN, license_plate, make, model, year, driver_id)
    VALUES (p_VIN, p_license_plate, p_make, p_model, p_year, p_driver_id);

    -- Insert into VehicleRegistrations table
    INSERT INTO vehicleregistrations (VIN, action, registration_date)
    VALUES (p_VIN, 'REGISTERED', CURDATE());

    COMMIT;
    SET p_result = 'Success: Vehicle registered successfully.';
END;
//
DELIMITER ;
DELIMITER //
CREATE PROCEDURE deregister_vehicle_transaction(
    IN p_VIN VARCHAR(17),
    OUT p_result VARCHAR(100)
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SET p_result = 'Error: Deregistration failed due to an issue.';
    END;

    START TRANSACTION;

    -- Delete from VehicleRegistrations table
    DELETE FROM vehicleregistrations
    WHERE VIN = p_VIN;

    -- Update the status of the vehicle in Vehicles table (mark it as inactive or similar)
    UPDATE vehicles
    SET status = 'INACTIVE'
    WHERE VIN = p_VIN;

    COMMIT;
    SET p_result = 'Success: Vehicle deregistered successfully.';
END;
//
DELIMITER ;
