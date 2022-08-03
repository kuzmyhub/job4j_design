create table ingredients(
id serial primary key,
name varchar(255)
);

create table dishes(
id serial primary key,
name varchar(255)
);

create table ingredients_dishes(
id serial primary key,
ingredient_id int references ingredients(id),
dish_id int references dishes(id)
);

insert into ingredients(name) values('potato');
insert into ingredients(name) values('onion');
insert into ingredients(name) values('vine');
insert into ingredients(name) values('milk');

insert into dishes(name) values('draniki');
insert into dishes(name) values('onion sup');
insert into dishes(name) values('mashed potato');

insert into ingredients(name) values('potato');
insert into ingredients(name) values('onion');
insert into ingredients(name) values('vine');
insert into ingredients(name) values('milk');

insert into dishes(name) values('draniki');
insert into dishes(name) values('onion sup');
insert into dishes(name) values('mashed potato');

insert into ingredients_dishes(ingredient_id, dish_id)
values (1, 1);
insert into ingredients_dishes(ingredient_id, dish_id)
values (1, 3);
insert into ingredients_dishes(ingredient_id, dish_id)
values (2, 1);
insert into ingredients_dishes(ingredient_id, dish_id)
values (2, 2);
insert into ingredients_dishes(ingredient_id, dish_id)
values (3, 2);
insert into ingredients_dishes(ingredient_id, dish_id)
values (4, 3);

select * from ingredients;
select * from dishes;
select * from ingredients_dishes;