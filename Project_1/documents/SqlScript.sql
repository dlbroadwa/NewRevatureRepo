
--Project 1 Tables Created 
--Created by Paityn Maynard on April 28, 2020
--Paityn Maynard: Added accounts, products, productTypes, and orders- April 28
--Perry Lee: Tweaked table structure- April 30

create table project1.accounts (
	user_id serial primary key,
	name varchar(30) not null,
	email varchar(60) unique not null,
	password varchar(30) not null,
	is_employee boolean not null default false,
	is_manager boolean not null default false
);

create table project1.product_types (
	id serial primary key,
	type_name varchar(200) not null
);

create table project1.products (
	product_id serial primary key,
	type_id integer references project1.product_types(id),
	product_name varchar(100) not null,
	price_cents integer not null check (price_cents >= 0),
	qty integer not null check (qty >= 0)
);

create table project1.orders (
	order_id serial primary key,
	customer_id integer references project1.accounts(user_id) on delete cascade,
	status_id integer
);

create table project1.order_items (
	order_id integer references project1.orders(order_id) on delete cascade,
	product_id integer references project1.products(product_id),
	sale_price integer not null check (sale_price >= 0),
	sale_qty integer not null check (sale_qty >= 0),
	primary key (order_id, product_id)
);
