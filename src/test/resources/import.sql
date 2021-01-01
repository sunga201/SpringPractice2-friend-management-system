insert into person(id, name, age, blood_type, year_of_birthday, month_of_birthday, day_of_birthday, job) values (1, 'martin', 10, 'A', 1996, 8, 15, 'engineer');
insert into person(id, name, age, blood_type, year_of_birthday, month_of_birthday, day_of_birthday) values (2, 'david', 9, 'A', 1992, 9, 7);
insert into person(id, name, age, blood_type, year_of_birthday, month_of_birthday, day_of_birthday) values (3, 'dannis', 11, 'B', 1990, 1, 13);
insert into person(id, name, age, blood_type, year_of_birthday, month_of_birthday, day_of_birthday) values (4, 'sophia', 12, 'AB', 1998, 8, 27);
insert into person(id, name, age, blood_type, year_of_birthday, month_of_birthday, day_of_birthday) values (5, 'benny', 7, 'O', 2001, 3, 7);

insert into block(id, name) values(1, 'martin');
insert into block(id, name) values(2, 'sophia');

update person set block_id = 1 where id = 1;
update person set block_id = 2 where id = 4;