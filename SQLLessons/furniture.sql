create table colorScheme(
id serial primary key,
base text,
additional text
);

create table furniture(
id serial primary key,
name text,
colorScheme_id int references colorScheme(id)
);

insert into colorScheme(base, additional) 
values ('white', 'gray');
insert into colorScheme(base) 
values ('beige');
insert into colorScheme(base, additional) 
values ('brown', 'black');
insert into colorScheme(base) 
values ('white');

insert into furniture(name, colorScheme_id) 
values ('wardrobe', 2);
insert into furniture(name, colorScheme_id) 
values ('bedside table', 3);
insert into furniture(name, colorScheme_id) 
values ('coffee table', 1);
insert into furniture(name, colorScheme_id) 
values ('boudoir', 4);
insert into furniture(name, colorScheme_id) 
values ('chair', 1);

select * from furniture
inner join colorScheme 
on furniture.colorScheme_id = colorScheme.id;

select f.name, c.base, c.additional from furniture as f
join colorScheme as c on f.colorScheme_id = c.id;

select f.name thing, c.base color from furniture f join
colorScheme c on f.colorScheme_id = c.id;

select f.name мебель, c.base as "Основной цвет" from furniture f join
colorScheme c on f.colorScheme_id = c.id;



