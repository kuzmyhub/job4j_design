create table departments(
id serial primary key,
name text
);

create table employees(
id serial primary key,
name text,
departments_id int references departments(id)
);

insert into departments(name)
values ('department of Fuel and energy Complex'),
('department of social and household Issues'),
('tax department'),
('department of Customs and tariff policy'),
('department of the ministry of finance');

insert into employees(name, departments_id) 
values ('Emma', 1), ('Olivia', 1), ('Ava', 1), 
('Sophia', 1), ('Isabella', 1), ('Mia', 2),
('Vanya', 2), ('Avgust', 2), ('Emma', 2),
('Charlotte', 4), ('Abigale', 4), ('Emily', 4), 
('Harper', 5);

select * from departments d
left outer join employees e
on d.id = e.departments_id;

select * from departments d
right outer join employees e
on d.id = e.departments_id;

select * from departments d
full outer join employees e
on d.id = e.departments_id;

select * from departments d
cross join employees;

select * from departments d
left outer join employees e
on d.id = e.departments_id;
select d.id, d.name, e.id, e.name, e.departments_id
from employees e
right outer join departments d
on d.id = e.departments_id;

create table teens(
id serial primary key,
name text,
gender text
);

insert into teens(name, gender) 
values  ('Ivan', 'male'), ('Rita', 'female'),
('Olga', 'female'), ('Semen', 'male'),
('Yura', 'male'), ('Tanya', 'female'),
('Nadya', 'female'), ('Genady', 'male'),
('Sergey', 'male'), ('Sasha', 'female');

select concat(t1.name, ' + ', t2.name) from teens t1
cross join teens t2
where t1.gender != t2.gender;