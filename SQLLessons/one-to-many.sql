create table specialty(
id serial primary key,
name varchar(255)
);

create table students(
id serial primary key,
name varchar(255),
specialty_id int references specialty(id)
);

insert into specialty(name) values ('construction');
insert into specialty(name) values ('programming');
insert into specialty(name) values ('economics');

insert into students(name, specialty_id)
values ('Zhenya', 2);
insert into students(name, specialty_id)
values ('Olga', 1);
insert into students(name, specialty_id)
values ('Viktor', 3);
insert into students(name, specialty_id)
values ('Sergey', 1);

select * from specialty;
select * from students;