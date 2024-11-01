CREATE SCHEMA IF NOT EXISTS product_manager;
USE product_manager;

create table user
(
    id        int auto_increment
        primary key,
    username  varchar(16)  not null,
    password  varchar(255) not null,
    firstname varchar(15)  not null,
    lastname  varchar(15)  not null,
    constraint username
        unique (username)
);



create table product
(
    id      int auto_increment
        primary key,
    name    varchar(20) not null,
    price   double      not null,
    user_id int         not null,
    constraint product_ibfk_1
        foreign key (user_id) references user (id)
);

create index user_id
    on product (user_id);

