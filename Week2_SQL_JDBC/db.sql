create schema myschema;

-- create DDL
create table if not exists public.users (
	id serial primary key, -- auto incrementing id
	email VARCHAR(30) not null unique,
	password char(40) not null
);

create table test (
	id serial primary key,
	val numeric(10,2)
);

-- drop DDL
drop table public.users;

-- You should know how to alter a table
alter table ...

-- insert DML
-- be sure to use single quotes for string values
insert into public.users (password , email) values ('0123456789012345678901234567890123456789', 'august.duet@revature.com');

insert into public.users (password , email) values
	('0123456789012345678901234567890123456789', 'john.doe@revature.com'),
	('0123456789012345678901234567890123456789', 'jane.doe@revature.com'),
	('0123456789012345678901234567890123456789', 'steve.irwin@revature.com');
	

-- update/delete DML
-- updates typically have a where clause
-- updates w/o a where clause will attempt to change the data for all rows
update public.users set email='august@revature.com' where id=1 and email='august.duet@revature.com';

-- deletes typically have a where clause
-- deletes w/o a where clauses will delete every row from the table
delete from public.users where email like 'august.%@%.com';

-- select DQL
select * from public.users;
select email as username, password as secret from public.users;







create schema flashcards;

create table if not exists flashcards.creator(
	id serial primary key,
	creator_name varchar(40) not null unique
);

create table if not exists flashcards.card (
	id serial primary key,
	question varchar(100) not null,
	creator_id int4 references flashcards.creator(id)
);

create user flashcards_user with encrypted password 'p@$$w0rd123';
grant all privileges on database postgres to flashcards_user;
grant all privileges on schema flashcards to flashcards_user;


insert into flashcards.creator (creator_name) values ('august');
insert into flashcards.creator (creator_name) values
	('john'), ('jane'), ('steve'), ('sara');












create table if not exists users (
	id serial primary key,
	first_name varchar(30) not null,
	last_name varchar(30),
	email varchar(50) not null unique,
	password char(40) not null
);

create table if not exists posts (
	id serial primary key,
	author_id integer references users(id) not null,
	wall_user_id integer references users(id) not null,
	content varchar(500) not null
);

create table if not exists likes (
	user_id integer references users(id) not null,
	post_id integer references posts(id) not null,
	primary key(user_id, post_id)
);

insert into users (first_name, last_name, email, password) values 
	('Hayden', 'Fields', 'hayden.fields@gmail.com', '1234567890123456789012345678901234567890'),
	('Abby', 'Addams', 'abby@gmail.com', '1234567890123456789012345678901234567890'),
	('Billy', 'Bob', 'bb@gmail.com', '1234567890123456789012345678901234567890'),
	('Cathy', 'Carter', 'cathy.carter@gmail.com', '1234567890123456789012345678901234567890'),
	('Danny', 'Davidson', 'danny.d@gmail.com', '1234567890123456789012345678901234567890');

insert into posts (author_id, wall_user_id, "content" ) values (1,2, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.'),
	(2,3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.'),
	(5,1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.'),
	(1,5, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.');

insert into likes (user_id, post_id) values (1,2), (2,4), (3,1), (3,4), (1,1);



























-- joins
-- INNER, LEFT, RIGHT, CROSS, FULL (OUTER), NATURAL, SELF, UNEQUAL
select author_id, content, wall_user_id from posts;

-- cross join
select * from users, posts; -- cartesian product

-- inner join
select first_name as author, posts.id, content as post from users
join posts on users.id = posts.author_id;

-- left join
select users.id, first_name, last_name, count(*) as count_num
	from users
	left join posts on posts.author_id = users.id
	group by users.id
	having count(*) > 1
	order by count_num desc;

-- right join in this example our right join counts the records from the inner join
select count(*) from users
	right join posts on users.id = posts.author_id;

-- indexes
-- Used for searching and reading data from a database
-- an index is a copy of a database table sorted by a certain column
-- this allows for a binary search algorithm to run on the table BTree
-- DBs automatically create indexes for PK and Uniqure columns
-- using indexes will increase the time of updates/deletes/inserts
create index author_id_index on posts(author_id);
select * from posts where author_id=1;
drop index posts_author_id_idx;

-- PL/SQL
-- Postgres plpgsql can written in many different languages including Java
-- Stored procedures and functions
-- Postgres doens't have stored procedures, only functions
-- Procedure is an operation that has no return (typically)
-- Function is an operation or group of operations store on the DB which
-- can be recalled later. They have access to DML/DQL commands
create or replace function my_sum(val_a INTEGER, val_b INTEGER)
returns INTEGER as $$ -- <-- body delimiter
	begin
		return val_a + val_b;
	end
$$ language plpgsql;


select my_sum(2,3);

create table if not exists store (
	id serial primary key,
	name varchar(20) not null unique
);

create or replace function new_store(store_name varchar(20))
returns void as $$
	begin 
		insert into store (name) values (store_name);
	end
$$ language plpgsql;

select * from store;
select new_store('fun store');
select * from store;

-- to return a set of rows to a user you must use a cursor
-- 1 problem -- in DBeaver or PGAdmin when you return a cursor
-- from a function the cursor is closed when the function finihes
-- executing.

-- Triggers some event condition that will execute some sql commands
create table if not exists colors (
	id serial primary key,
	color varchar(40)
);

insert into colors (color) values ('blue'), ('red'), ('green'), ('purple'), ('gold'), ('orange'), ('brown'), ('black'),
	('silver'), ('magenta');

-- Event Types Before, After, Instead
-- Event Operation Insert, Update, Delete

-- TG_OP trigger operation
-- NEW incoming data for an insert or update
-- OLD data that already exist in the database
create or replace function no_blues()
returns trigger as $$
	begin
		if(TG_OP = 'INSERT') then
			if(UPPER(new.color) = 'BLUE') then -- upper('blue') = 'BLUE'
				return null; -- prevents the insert from happening
			end if;
		end if;
		if(UPPER(new.color) = 'BLUE') then -- only for update
			return old; -- don't modify the old data
		end if;
		return new; -- otherwise let the update or insert happen
	end
$$ language plpgsql;

create trigger no_blues_trigger
	before insert or update on colors
	for each row
	execute procedure no_blues();

insert into colors (color) values ('pink');
select * from colors;
insert into colors (color) values ('blue');
update colors set color='blue' where color='gold';


-- sequences
create sequence my_incr
	start 1000
	minvalue 1000
	maxvalue 1010
	cycle
	increment by 2;

drop sequence my_incr;
select nextval('my_incr');

create table test_table (
	id integer primary key,
	val boolean
);

insert into test_table (id, val) values (nextval('my_incr'), true), (nextval('my_incr'), false), (nextval('my_incr'), false);
select * from test_table;

-- SQL Sublanguages
-- How to write statements (syntax)
-- clauses where, having, group by, order by
-- joins
-- JDBC
-- PL/SQL
-- Transaction Properties (ACID)










