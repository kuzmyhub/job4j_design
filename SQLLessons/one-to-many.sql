create table products(
id serial primary key,
name varchar(255)
);

create table electronicsManufacture(
id serial primary key,
name varchar(255),
products_id int references products(id)
);

insert into products(name) values ('phone');
insert into products(name) values ('hairdryer');
insert into products(name) values ('irrigator');
insert into electronicsManufacture(name, products_id) VALUES ('LD', 1);
insert into electronicsManufacture(name, products_id) VALUES ('LD', 2);
insert into electronicsManufacture(name, products_id) VALUES ('LD', 3);
insert into electronicsManufacture(name, products_id) VALUES ('Xioomy', 1);
insert into electronicsManufacture(name, products_id) VALUES ('Sonya', 1);

select * from products;
select * from electronicsManufacture where id in (select id from products);
