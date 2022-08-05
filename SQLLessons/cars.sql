create table car_bodies(
id serial primary key,
name text
);

create table car_engines(
id serial primary key,
name text
);

create table car_transmissions(
id serial primary key,
name text
);

create table cars(
id serial primary key,
name text,
body_id int references car_bodies(id),
engine_id int references car_engines(id),
transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) 
values ('hatchback'), ('universal'),
('pickup'), ('convertible'), ('coupe');

insert into car_engines(name) 
values ('inline'), ('v-type'), ('rotary'), ('radial');
select * from car_engines;

insert into car_transmissions(name) 
values ('mechanical'), ('automatic'),
('robot'), ('variator');
select * from car_transmissions;

insert into cars(name, body_id, engine_id, transmission_id) 
values ('Volkswagen', 1, 1, 1),
('Volvo', 2, 2, 2),
('Nissan', 3, 3, 3),
('Geely', 1, 2, 3),
('Chance', 2, 1, 1),
('Lada', 2, 3, 2),
('Ford', 2, 1, 2),
('Kia', 3, 3, 2);
insert into cars(name, engine_id, transmission_id) 
values ('Porshe', 1, 1);
insert into cars(name, body_id, transmission_id) 
values ('BMW', 1, 3);
insert into cars(name, body_id, engine_id) 
values ('BMW', 2, 2);
insert into cars(name) 
values ('Toyota');
select * from cars;

select c.name car, b.name body, e.name engine, t.name transmission
from cars c
left outer join car_bodies b 
on c.body_id = b.id
left outer join car_engines e
on c.engine_id = e.id
left outer join car_transmissions t
on c.transmission_id = t.id;

select b.name body from cars c
right outer join car_bodies b
on c.body_id = b.id
where c.name is null;

select e.name engines from cars c
right outer join car_engines e
on c.engine_id = e.id
where c.name is null;

select t.name transmission from cars c
right outer join car_transmissions t
on c.transmission_id = t.id
where c.name is null;
