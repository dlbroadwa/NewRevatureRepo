create schema InventoryApp;
DROP SCHEMA "schema";

create table if not exists InventoryApp.users
(	id serial primary key,
	email VARCHAR(19) not null unique,
	password char(10) not null);
insert into inventoryapp.users (email, password) values ('dlbroadwa@gmail.com', 'password12');

create table if not exists InventoryApp.pockets
(	id serial primary key,
	balance decimal(7,2));
insert into inventoryapp.pockets (balance) values (5000.00);

create table if not exists InventoryApp.inventory
(	item_id serial primary key,
	item VARCHAR(10),
	quantity int,
	itemprice decimal(4,2)
	);
	
insert into inventoryapp.inventory (
	item_id, item, quantity, itemprice
)
values
	(1, 'candy1', 400, 00.25),
	(2, 'candy2', 350, 00.40),
	(3, 'candy3', 300, 00.35),
	(4, 'candy4', 200, 00.55);
alter table inventoryapp.inventory 
drop column quantity;
	
create table if not exists InventoryApp.current_inventory
(	item_id serial primary key,
	item VARCHAR(10),
	quantity int
	);
	
insert into inventoryapp.current_inventory (
	item_id, item, quantity
)
values
	(1, 'candy1', 400),
	(2, 'candy2', 350),
	(3, 'candy3', 300),
	(4, 'candy4', 200);
	
create user dlbroadw with encrypted password 'password';
grant all privileges on database postgres to dlbroadw;