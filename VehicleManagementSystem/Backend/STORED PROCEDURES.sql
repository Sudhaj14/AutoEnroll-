USE vehiclemanagementsystem;
delimiter //
create procedure register_vehicle(
IN p_vin varchar(50),
IN p_license_plate varchar(50),
IN p_make varchar(50),
IN p_model varchar(50),
IN p_year int,
OUT p_result varchar(100)
)
begin
declare vehicle_exists int;

select count(*) into vehicle_exists
from vehicles
where VIN = p_vin;

if vehicle_exists > 0 then
set p_result = 'Error:Vehicle already registered';

else
insert into Vehicles(VIN,license_plate,make,model,year,registration_date)
values(p_vin,p_license_plate,p_make,p_model,p_year,CUrRENT_TIMESTAMP);

INSERT INTO vehicleregistrations(VIN,action,registered_date)
values(p_vin,'Registered',current_timestamp());

SET p_result = 'successfully registeres';
END IF;
end //
DELIMITER ;
CALL register_vehicle('VIN1245', 'AC123', 'Toyota', 'Camry', 2022, @result);
SELECT @result;
