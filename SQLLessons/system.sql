create table role (
id serial primary key,
name text
);

create table rules (
id serial primary key,
name text
);

create table role_rules (
id serial primary key,
role_id int references role(id),
rules_id int references rules(id)
);

create table users (
id serial primary key,
name text,
role_id int references role(id)
);

create table category(
id serial primary key,
name text
);

create table state(
id serial primary key,
name text
);

create table item (
id serial primary key,
name text,
users_id int references users(id),
category_id int references category(id),
state_id int references state(id)
);

create table comments(
id serial primary key,
name text,
item_id int references item(id)
);

create table attachs (
id serial primary key,
name text,
item_id int references item(id)
);

insert into role(name) values ('Sysadmin');
insert into role(name) values ('Tester');
insert into role(name) values ('Layout designer');

insert into rules(name) values ('Full access');
insert into rules(name) values ('Limited access');

insert into role_rules(role_id, rules_id)
values (1, 1);
insert into role_rules(role_id, rules_id)
values (2, 2);
insert into role_rules(role_id, rules_id)
values (3, 2);


insert into users(name, role_id) values ('Ivan', 1);
insert into users(name, role_id) values ('Sergey', 2);
insert into users(name, role_id) values ('Igor', 3);

insert into category(name) values ('High priority');
insert into category(name) values ('Medium priority');
insert into category(name) values ('Low priority');

insert into state(name) values ('Not viewed');
insert into state(name) values ('Viewed');
insert into state(name) values ('Completed');

insert into item(name, users_id, category_id, state_id)
values ('Recover DB', 1, 1, 3);
insert into item(name, users_id, category_id, state_id)
values ('Test new method', 2, 2, 1);
insert into item(name, users_id, category_id, state_id)
values ('Add feature', 3, 3, 2);

insert into comments(name, item_id)
values ('The base has been restored', 1);
insert into comments(name, item_id)
values ('Waiting for the customers approval', 3);

insert into attachs(name, item_id) 
values ('/emails/OfficialLetter.docx', 3);
