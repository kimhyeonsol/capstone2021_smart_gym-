INSERT INTO manager (manager_password, manager_login_status) SELECT '0000', 'false' WHERE NOT EXISTS (SELECT manager_password FROM manager WHERE manager_password = '0000');
INSERT INTO gym_info (gym_info_id, gym_info_address, gym_info_equipment_layout, gym_info_name, gym_info_phone_number) SELECT 1, ' ', ' ', ' ', ' ' WHERE NOT EXISTS (SELECT * FROM gym_info WHERE gym_info_id = 1);
INSERT INTO gym_operation_info (gym_operation_info_id, gym_operation_info_operating_start_time, gym_operation_info_operating_end_time, gym_operation_info_regular_holiday, gym_operation_info_reservation_duration) SELECT 1, '00:00:00', '23:59:00', ' ', '7' WHERE NOT EXISTS (SELECT * FROM gym_operation_info WHERE gym_operation_info_id = 1);