insert into pharmacy(id, address, average_score, name, medication_ids) values (1,'Adresa1',1,'Apoteka1','1,2,3');
insert into pharmacy(id, address, average_score, name, medication_ids) values (2,'Adresa2',2,'Apoteka2','1,2');
insert into pharmacy(id, address, average_score, name, medication_ids) values (3,'Adresa3',3,'Apoteka3','1,2,3');

insert into dermatologist(id, first_name, last_name, pharmacy) values (1,'Ana','Pavlov', '1,2');
insert into dermatologist(id, first_name, last_name, pharmacy) values (2,'Pera','Petrov', '1,3');
insert into dermatologist(id, first_name, last_name, pharmacy) values (3,'Aca','Dizdar', '2,3');

insert into pharmacist(id, first_name, last_name, pharmacy, start_time, end_time) VALUES (1,'Marko','Markov',3,'00:00:00','08:00:00');
insert into pharmacist(id, first_name, last_name, pharmacy, start_time, end_time) VALUES (4,'Milan','Markov',3,'08:00:00','14:00:00');
insert into pharmacist(id, first_name, last_name, pharmacy, start_time, end_time) VALUES (2,'Marija','Dimitrov',1,'15:00:00','21:00:00');
insert into pharmacist(id, first_name, last_name, pharmacy, start_time, end_time) VALUES (3,'Biljana','Horvat',2,'15:00:00','21:00:00');
insert into pharmacist(id, first_name, last_name, pharmacy, start_time, end_time) VALUES (5,'Ivica','Jovic',2,'15:00:00','21:00:00');


insert into medication(id, name, pharmacyid, alergiesid, price, new_price_date, amount) VALUES (1,'Brufen', '1','1', 100, '12-03-2021',10);
insert into medication(id, name, pharmacyid, alergiesid, price, new_price_date,amount) VALUES (2,'Paracetamol', '2', null, 200, '12-05-2021',10);
insert into medication(id, name, pharmacyid, alergiesid, price, new_price_date,amount) VALUES (3,'Lek za kasalj', '3', null, 300, '12-04-2021',10);
insert into medication(id, name, pharmacyid, alergiesid, price, new_price_date,amount) VALUES (4,'Septolete', '3', null, 300, '12-04-2021',10);

insert into allergie(id, name) values (1,'Alergija1');
insert into allergie(id, name) values (2,'Alergija2');
insert into allergie(id, name) values (3,'Alergija3');

insert into profile(id, username,email, first_name, last_name, password, alergies_ids) VALUES (1,'pera123','email@mail.com','Pera', 'Peric', '123', '1');
insert into profile(id, username,email, first_name, last_name, password, alergies_ids) VALUES (2,'mara123','mara@mail.com','Mara', 'Maric', '123', '2');
insert into profile(id, username,email, first_name, last_name, password, alergies_ids) VALUES (3,'admin2','mara2@mail.com','Mara', 'Maric', '123', '2');
insert into profile(id, username,email, first_name, last_name, password, alergies_ids) VALUES (4,'admin3','mara3@mail.com','Mara', 'Maric', '123', '2');


insert into dermatologist_work_time(id, dermatologist_id, pharmacy_id, shift_start, shift_end) values (1, 1, 1, '8:30', '13:00' );
insert into dermatologist_work_time(id, dermatologist_id, pharmacy_id, shift_start, shift_end) values (2, 2, 1, '12:30', '20:00' );
insert into dermatologist_work_time(id, dermatologist_id, pharmacy_id, shift_start, shift_end) values (3, 3, 2, '9:30', '15:00' );
insert into dermatologist_work_time(id, dermatologist_id, pharmacy_id, shift_start, shift_end) values (4, 1, 2, '13:30', '20:00' );
insert into dermatologist_work_time(id, dermatologist_id, pharmacy_id, shift_start, shift_end) values (5, 2, 3, '8:30', '12:00' );
insert into dermatologist_work_time(id, dermatologist_id, pharmacy_id, shift_start, shift_end) values (6, 3, 3, '15:30', '21:00' );

insert into dermatologist_appointment(id, appointment_start, appointment_end, date, dermatologist_id, is_done, patient_id, pharmacy_id, price, duration) values (1,'12:30','13:00', (NOW()::date + interval '2 day'), 1, FALSE, -1, 1, 100, 30);
insert into dermatologist_appointment(id, appointment_start, appointment_end, date, dermatologist_id, is_done, patient_id, pharmacy_id, price, duration) values (2,'13:10','13:30', (NOW()::date + interval '3 day'), 1, FALSE, -1, 1, 200, 30);
insert into dermatologist_appointment(id, appointment_start, appointment_end, date, dermatologist_id, is_done, patient_id, pharmacy_id, price, duration) values (3,'13:30','14:00', (NOW()::date + interval '4 day'), 2, FALSE, -1, 2, 200, 30);

insert into pharmacist_appointment(id, appointment_start, appointment_end, date, pharmacist_id, is_done, patient_id, pharmacy_id, price, duration) VALUES (1,'12:30','13:00', (NOW()::date + interval '2 day'), 1, FALSE, -1, 1, 100, 30);
insert into pharmacist_appointment(id, appointment_start, appointment_end, date, pharmacist_id, is_done, patient_id, pharmacy_id, price, duration) VALUES (2,'12:30','13:00', (NOW()::date + interval '3 day'), 2, FALSE, -1, 2, 300, 30);
insert into pharmacist_appointment(id, appointment_start, appointment_end, date, pharmacist_id, is_done, patient_id, pharmacy_id, price, duration) VALUES (3,'12:30','13:00', (NOW()::date + interval '4 day'), 3, FALSE, -1, 3, 200, 30);
insert into pharmacist_appointment(id, appointment_start, appointment_end, date, pharmacist_id, is_done, patient_id, pharmacy_id, price, duration) VALUES (4,'11:00','12:30', (NOW()::date + interval '2 day'), 1, FALSE, -1, 1, 100, 30);
insert into pharmacist_appointment(id, appointment_start, appointment_end, date, pharmacist_id, is_done, patient_id, pharmacy_id, price, duration) VALUES (5,'11:00','12:30', (NOW()::date + interval '3 day'), 2, FALSE, -1, 2, 500, 30);
insert into pharmacist_appointment(id, appointment_start, appointment_end, date, pharmacist_id, is_done, patient_id, pharmacy_id, price, duration) VALUES (6,'11:00','12:30', (NOW()::date + interval '4 day'), 3, FALSE, -1, 3, 600, 30);
insert into pharmacist_appointment(id, appointment_start, appointment_end, date, pharmacist_id, is_done, patient_id, pharmacy_id, price, duration) VALUES (7,'11:00','12:30', (NOW()::date + interval '4 day'), 4, FALSE, -1, 1, 700, 30);
insert into pharmacist_appointment(id, appointment_start, appointment_end, date, pharmacist_id, is_done, patient_id, pharmacy_id, price, duration) VALUES (8,'11:00','12:30', (NOW()::date + interval '4 day'), 5, FALSE, -1, 2, 800, 30);

insert into user_roles(id, role, user_id, pharmacy_id) VALUES (1,1,1,1);
insert into user_roles(id, role, user_id, pharmacy_id) VALUES (3,1,3,2);
insert into user_roles(id, role, user_id, pharmacy_id) VALUES (4,1,4,3);
--obican user je null i 2
insert into user_roles(id, role, user_id, pharmacy_id) VALUES (2,2,2,null);

insert into action(id, name, medicationid, description, new_price, price, is_active, end_date, pharmacy_id) VALUES (1,'Kupovina brufena', 1, 'Snizena cena brufena na 200 sa 500', 200, 500, true, '13-02-2022', '1');
insert into promotion(id, name, medicationid, description, amount_of_product, price, is_active, end_date, pharmacy_id) VALUES (1,'Krema za lice', 1, 'Za 2300 dobijate 3 kreme', 3, 2300, true, '13-02-2022', '2');

insert into vacation(id, employee_id, is_pharmacist, reasoning, response,approved, is_new) VALUES (2, 1, false, '', '', false, true);
insert into paid_leave(id, employee_id, is_pharmacist, reasoning, response,approved, is_new) VALUES (2, 1, false, '', '', false, true);
insert into paid_leave(id, employee_id, is_pharmacist, reasoning, response,approved, is_new) VALUES (1, 1, true, '', '', false, true);