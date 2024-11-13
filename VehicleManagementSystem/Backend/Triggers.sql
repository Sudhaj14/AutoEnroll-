use vehiclemanagementsystem
DELIMITER //
CREATE TRIGGER before_insert_vehicle
BEFORE INSERT ON vehicles
FOR EACH ROW
BEGIN
    DECLARE vin_exists INT;

    -- Check if the VIN already exists in the table
    SELECT COUNT(*) INTO vin_exists
    FROM vehicles
    WHERE VIN = NEW.VIN;

    IF vin_exists > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: VIN already exists in the Vehicles table';
    END IF;
END;
//
DELIMITER ;
DELIMITER //
CREATE TRIGGER after_insert_vehicle_registration
AFTER INSERT ON vehicleregistrations
FOR EACH ROW
BEGIN
    -- Update the registration_date in the Vehicles table
    UPDATE vehicles
    SET registration_date = NEW.registration_date
    WHERE VIN = NEW.VIN;
END;
//
DELIMITER ;
