create table devices(
id serial primary key,
name varchar(255),
price float
);

create table people(
id serial primary key,
name varchar(255)
);

create table devices_people(
id serial primary key,
device_id int references devices(id),
people_id int references people(id)
);

insert into devices(name, price) 
values ('printer', 10299.99);
insert into devices(name, price) 
values ('monitor', 6899.59);
insert into devices(name, price) 
values ('keyboard', 1499.99);
insert into devices(name, price) 
values ('phone', 24599.99);
insert into devices(name, price) 
values ('notebook', 77999.99);
insert into devices(name, price) 
values ('makbook', 129999.99);
insert into devices(name, price) 
values ('watch', 16599.89);

insert into people(name) values ('Ivan');
insert into people(name) values ('Zhenya');
insert into people(name) values ('Olga');
insert into people(name) values ('Shasha');

insert into devices_people(device_id, people_id) 
values (7, 1), (7, 3), (7, 4);
insert into devices_people(device_id, people_id) 
values (6, 1);
insert into devices_people(device_id, people_id) 
values (5, 2), (5, 3);
insert into devices_people(device_id, people_id) 
values (4, 1), (4, 2), (4, 3), (4, 4);
insert into devices_people(device_id, people_id) 
values (3, 1), (3, 2), (3, 3), (3, 4);
insert into devices_people(device_id, people_id) 
values (2, 1), (2, 2), (2, 3), (2, 4);
insert into devices_people(device_id, people_id) 
values (1, 1);

select avg(price) from devices;

select p.name, avg(d.price) 
from people p inner join
devices_people dp on
p.id = dp.people_id inner join
devices d on
d.id = dp.device_id
group by p.name;

select p.name, avg(d.price)
from people p join
devices_people dp on
dp.people_id = p.id join
devices d on
dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;