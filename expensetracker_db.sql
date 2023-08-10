--delete database (for testing purpose)
drop database expensetrackerdb;
--delete user (for testing purpose)
drop user expensetracker;
--create an user
create user expensetracker with password 'password';
--create database using pre-defined template and define owner
create database expensetrackerdb with template=template0 owner=expensetracker;
--connect with db
connect expensetrackerdb;
--to create any db object, user needs privileges
alter default privileges grant all on tables to expensetracker;
--sequences to generate primary keys
alter default privileges grant all on sequences to expensetracker;

--3 tables

--users table
create table et_users(
user_id integer primary key not null,
first_name varchar(20) not null,
last_name varchar(20) not null,
email varchar(30) not null,
password text not null
);

--category table
--1:M relation with users
create table et_categories(
category_id integer primary key not null,
user_id integer not null,
title varchar(20) not null,
description varchar(50) not null
);

--add constraint for foreign key user_id
alter table et_categories add constraint cat_users_fk
foreign key (user_id) references et_users(user_id);

--transactions table
--1:M relation with category
create table et_transactions(
transaction_id integer primary key not null,
category_id integer not null,
user_id integer not null,
amount numeric(10, 2) not null,
note varchar(50) not null,
transaction_timestamp bigint not null
);

--add constraints for foreign keys
--category_id and user_id
alter table et_transactions add constraint trans_cat_fk
foreign key (category_id) references et_categories(category_id);
alter table et_transactions add constraint trans_users_fk
foreign key (user_id) references et_users(user_id);

--create sequences for all 3 tables primary key

create sequence et_users_seq increment 1 start 1;
create sequence et_categories_seq increment 1 start 1;
create sequence et_transactions_seq increment 1 start 1000;

