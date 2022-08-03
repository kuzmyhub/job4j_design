create table dna(
id serial primary key;
marking varchar(255);
code int;
);

create table people(
id serial primary key;
name varchar(255);
);

create table dna_people(
id serial primary key;
id_dna int references dna(id) unique;
id_people int references people(id) unique;
);

insert into dna(marking, code) values ('A', 234);
insert into dna(marking, code) values ('C', 811);
insert into dna(marking, code) values ('D', 925);

insert into people(name) values ('Ivan Knyazev');
insert into people(name) values ('Andrey Shabov');
insert into people(name) values ('Kiril Makinov');

insert into dna_people(id_dna, id_people) values (1, 3);
insert into dna_people(id_dna, id_people) values (2, 1);
insert into dna_people(id_dna, id_people) values (3, 2);

select * from dna;
select * from people;
select * from dna_people;