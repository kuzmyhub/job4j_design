create table fauna(
id serial primary key,
name text,
avg_age int,
disocvery_date date
);

insert into fauna(name, avg_age, discovery_date)
values ('gold fish', 5490, date '1716-02-10');
insert into fauna(name, avg_age)
values ('brown bear', 9150);
insert into fauna(name, avg_age)
values ('elephant', 29790);
insert into fauna(name, avg_age, discovery_date) 
values ('pieris brassicae', 75, date '1758-05-15');
insert into fauna(name, avg_age, discovery_date) 
values ('chamelion', 5475, date '1880-08-20');
insert into fauna(name, avg_age) 
values ('pica pica', 4745);
insert into fauna(name, avg_age, discovery_date) 
values ('ceratophora stoddartii', 2900, date '2019-08-20');
insert into fauna(name, avg_age, discovery_date) 
values ('deutscher Schäferhund', 20000, date '1899-01-01');

select * from fauna where name like '%fish%';
select * from fauna 
where avg_age < 21000 and avg_age > 10000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';
