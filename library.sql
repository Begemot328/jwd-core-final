drop schema if exists library;

create schema if not exists library;

use library;

create table book_status
(
    id           tinyint primary key auto_increment,
    name         varchar(40) not null,
    picture_link varchar(40)
);

insert book_status (id, name)
values (1, 'non-existent');
insert book_status (id, name)
values (2, 'ready');
insert book_status (id, name)
values (3, 'sold');
insert book_status (id, name)
values (4, 'reserved');
insert book_status (id, name)
values (5, 'transfer');


create table user_status
(
    id   tinyint primary key,
    name varchar(40) not null
);

insert user_status (id, name)
values (0, 'non-existent');
insert user_status (id, name)
values (1, 'disabled');
insert user_status (id, name)
values (2, 'reader');
insert user_status (id, name)
values (3, 'librarian');
insert user_status (id, name)
values (4, 'admin');

create table users
(
    id         int primary key auto_increment,
    first_name varchar(40) not null,
    last_name  varchar(40) not null,
    login      varchar(10) not null,
    password   int(10)     not null,
    adress     varchar(40) not null,
    photo_link varchar(40)
);

create index id_index on users (id);
create index lastname_index on users (last_name);

create table user_actions
(
    id             int primary key auto_increment not null,
    id_user        int                            not null,
    id_admin       int                            not null,
    date           date,
    initial_status tinyint                        not null,
    final_status   tinyint                        not null,

    foreign key (id_user) references users (id),
    foreign key (id_admin) references users (id),
    foreign key (initial_status) references user_status (id),
    foreign key (final_status) references user_status (id)
);

create index id_index on user_actions (id);
create index user_index on user_actions (id_user);
create index admin_index on user_actions (id_admin);

insert users(first_name, last_name, login, password, adress)
values ('Yury', 'Zmushko', 'root', 1234567890, #change to hash
        'unknown');

create table books
(
    id          int primary key auto_increment,
    title       varchar(40) not null,
    author      varchar(40) not null,
    description text
);

create index id_index on books (id);
create index title_index on books (title);
create index author_index on books (author);

create table shops
(
    id       int primary key auto_increment not null,
    name     varchar(40)                    not null,
    position varchar(40)
);

create index id_index on shops (id);

create table positions
(
    id       int primary key auto_increment,
    book_id  int,
    shop_id  int,
    status   tinyint not null,
    note     text,
    quantity int     not null default 1,
    foreign key (status) references book_status (id),
    foreign key (shop_id) references shops (id)
);

create index id_index on positions (id);
create index book_index on positions (book_id);
create index shop_index on positions (shop_id);

create table book_actions
(
    id               int primary key auto_increment not null,
    book_id          int                            not null,
    reader_id        int,
    librarian_id     int                            not null,
    date             date,
    quantity         int                            not null default 1,
    initial_status   tinyint                        not null,
    final_status     tinyint                        not null,
    initial_position int                            not null,
    final_position   int                            not null,

    foreign key (initial_status) references book_status (id),
    foreign key (final_status) references book_status (id),
    foreign key (initial_position) references positions (id),
    foreign key (final_position) references positions (id),
    foreign key (book_id) references books (id),
    foreign key (reader_id) references users (id),
    foreign key (librarian_id) references users (id)
);

create index id_index on book_actions (id);
create index book_index on book_actions (book_id);
create index librarian_index on book_actions (librarian_id);
create index reader_index on book_actions (reader_id);




