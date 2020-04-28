
--Project 1 Tables Created 
--Created by Paityn Maynard on April 28, 2020
--Paityn Maynard: Added accounts, products, productTypes, and orders- April 28

create table accounts(
name VARCHAR(30),
email VARCHAR(60) primary key,
password VARCHAR(30) NOT NULL,
employee Boolean default false,
manager Boolean default false
);

create table products(
product_id SERIAL primary key,
type_id INTEGER,
product_name VARCHAR(100),
price_cents INTEGER,
qty INTEGER
);

create table productTypes(
type_id INTEGER,
type VARCHAR(200)
);

create table orders(
orderNumber SERIAL,
email VARCHAR(60),
product_id INTEGER,
qty INTEGER,
total INTEGER
);


