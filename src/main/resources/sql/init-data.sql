
insert into role(id, name, description, create_date) values (1, 'Admin', 'Admin User', CURRENT_DATE());

insert into user(id, username, password, last_name, first_name, role_id, create_date) values (1, 'admin', '123', 'Leang', 'Sotheara', 1, CURRENT_DATE());
insert into user(username, password, last_name, first_name, create_date) values ('sok', 'sok', 'Ly', 'Sok', CURRENT_DATE());
insert into user(username, password, last_name, first_name, create_date) values ('vathna', '123', 'Lay', 'Vathana', CURRENT_DATE());
insert into user(username, password, last_name, first_name, create_date) values ('sovan', 'dara', 'Sok', 'Sovannara', CURRENT_DATE());
insert into user(username, password, last_name, first_name, create_date) values ('sinara', 'dara', 'Lay', 'Sinara', CURRENT_DATE());
