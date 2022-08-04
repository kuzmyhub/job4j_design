create table type(
id serial primary key,
name text
);

create table product(
id serial primary key,
name text,
type_id int references type(id),
expired_date date,
price float
);

insert into type(name) values ('cheese');
insert into type(name) values ('fruits');
insert into type(name) values ('vegetables');
insert into type(name) values ('meat');
insert into type(name) values ('milk');

insert into product(name, type_id, expired_date, price)
values ('icecream', 5, date '2022-10-13', 82.99);
insert into product(name, type_id, expired_date, price)
values ('cows', 5, date '2022-08-03', 60.90);
insert into product(name, type_id, expired_date, price)
values ('goats', 5, date '2022-08-15', 61.90);
insert into product(name, type_id, expired_date, price)
values ('mozzarella', 1, date '2022-09-20', 243.59);
insert into product(name, type_id, expired_date, price)
values ('gorgonzola', 1, date '2022-09-25', 289.99);
insert into product(name, type_id, expired_date, price)
values ('suluguni', 1, date '2022-09-28', 269.99);
insert into product(name, type_id, expired_date, price)
values ('imeretian', 1, date '2022-09-19', 219.99);
insert into product(name, type_id, expired_date, price)
values ('russian', 1, date '2022-09-16', 209.99);
insert into product(name, type_id, expired_date, price)
values ('dutch', 1, date '2022-09-11', 299.99);
insert into product(name, type_id, expired_date, price)
values ('mazdam', 1, date '2022-09-12', 289.99);
insert into product(name, type_id, expired_date, price)
values ('tilsiter', 1, date '2022-09-11', 309.99);
insert into product(name, type_id, expired_date, price)
values ('gouda', 1, date '2022-08-21', 319.99);
insert into product(name, type_id, expired_date, price)
values ('creamy', 1, date '2022-08-01', 349.99);
insert into product(name, type_id, expired_date, price)
values ('parmesan', 1, date '2022-12-20', 449.99);
insert into product(name, type_id, expired_date, price)
values ('apples', 2, date '2022-08-10', 139.9);
insert into product(name, type_id, expired_date, price)
values ('bananas', 2, date '2022-08-11', 69.69);
insert into product(name, type_id, expired_date, price)
values ('orange', 2, date '2022-07-29', 99.99);
insert into product(name, type_id, expired_date, price)
values ('cabage', 3, date '2022-08-29', 49.59);
insert into product(name, type_id, expired_date, price)
values ('tomato', 3, date '2022-08-21', 160.69);
insert into product(name, type_id, expired_date, price)
values ('potato', 3, date '2022-09-08', 79.59);
insert into product(name, type_id, expired_date, price)
values ('chiken', 4, date '2022-08-19', 234.99);
insert into product(name, type_id, expired_date, price)
values ('pork', 4, date '2022-08-18', 299.99);

select p.name from product p
inner join type t on
p.type_id = t.id
where t.name = 'cheese';

select name from product where name like '%icecream%';

select name from product where expired_date < current_date;

select name from product 
where price = (select max(price) from product);

select t.name, count(p.name) from type t
inner join product p on
t.id = p.type_id
group by t.name;

select p.name from product p
inner join type t on
p.type_id = t.id
where t.name = 'cheese' or t.name = 'milk';

select t.name from type t
inner join product p on
t.id = p.type_id
group by t.name
having count(p.name) < 10;

select p.name, t.name 
from product p
inner join type t 
on p.type_id = t.id;

