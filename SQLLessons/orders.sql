create table orders(
id serial primary key,
name text,
quantity integer,
cost money
);

insert into orders (name, quantity, cost)
values ('Petr Arsentev', 6, 1220.50);

update orders set name = 'Иван Шагов';

delete from orders;


