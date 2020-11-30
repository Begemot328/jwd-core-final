drop schema if exists library;

create schema if not exists library;

use library;

create table book_status
(
    id   tinyint primary key auto_increment,
    name varchar(40) not null
);

insert book_status (id, name)
values (1,'ready');
insert book_status (id, name)
values (2,'taken home');
insert book_status (id, name)
values (3,'taken to reading room');
insert book_status (id, name)
values (4,'reserved');
insert book_status (id, name)
values (0,'non-existent');

create table user_status
(
    id   tinyint primary key auto_increment,
    name varchar(40) not null
);

insert user_status (id, name)
values (0,'non-existent');
insert user_status (id, name)
values (1,'disabled');
insert user_status (id, name)
values (2,'reader');
insert user_status (id, name)
values (3,'librarian');
insert user_status (id, name)
values (4,'admin');

create table users
(
    id   tinyint primary key auto_increment,
    first_name varchar(40) not null,
    last_name varchar(40) not null,
    login varchar(10) not null,
    password int(10) not null,
    adress varchar(40)  not null
);

create table user_actions
(
    id   int primary key auto_increment not null,
    id_user tinyint not null,
    id_admin tinyint not null,
    start_date date,
    planned_end_date date,
    actual_end_date date,
    initial_status tinyint,
    final_status tinyint not null,

    foreign key (id_user) references users(id),
    foreign key (id_admin) references users(id),
    foreign key (initial_status) references user_status(id),
    foreign key (final_status) references user_status(id)
);

insert users(first_name, last_name, login, password, adress)
values ('Yury', 'Zmushko', 'root', 'rootpaswordhash' #change to hash
'unknown');

create table books
(
    id   tinyint primary key auto_increment,
    name varchar(40) not null,
    author varchar(40) not null,
    status tinyint not null default 1, #may be uneccessary
    foreign key (status) references book_status (id)
);

create table book_actions
(
    id   int primary key auto_increment not null,
    id_book tinyint not null,
    id_reader tinyint not null,
    id_librarian tinyint not null,
    start_date date,
    planned_end_date date,
    actual_end_date date,
    temporal_status tinyint not null,

    foreign key (temporal_status) references book_status (id),
    foreign key (id_book) references books(id),
    foreign key (id_reader) references users(id),
    foreign key (id_librarian) references users(id)
);

