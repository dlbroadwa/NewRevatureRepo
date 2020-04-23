create schema mySchema;

-- TUESDAY SCRIPTS (4/14)

-- create DDL
create table if not exists public.users (
	id serial primary key, -- auto incrementing id
	email VARCHAR(30) not null unique,
	password char(40) not null 
);

-- VARCHAR(Length AND Precision)
-- char(Length AND Precision)

create table if not exists test (
	id serial primary key,
	val numeric(10,2)
);

-- numeric(Length, Precision)

-- drop DDL
drop table public.users; 

-- I should know how to alter a table
-- when altering, everything depends on what you're altering
-- alter table ...
-- alter table tablename
-- and so on, there's a LOT to cover

-- insert DML
-- ORDER MATTERS IN SYNTAX
-- be sure to use single quotes for string values
insert into public.users (password, email) values ('1234567890098765432113579246800864297531', 'lastname@emailservice.com');

insert into public.users (password, email) values 
	('1234567890098765432113579246800864297531', 'firstname@emailservice.com'),
	('1234567890098765432113579246800864297531', 'johndoe@emailservice.com'),
	('1234567890098765432113579246800864297531', 'janedow@emailservice.com');

-- update/delete DML
-- updates typically have a where clause
-- updates WITHOUT a where clause will attempt to change the data for ALL ROWS
update public.users set email='bmartino@emailservice.com' where id=7; --and email='firstname@emailservice'

-- deletes typically have a where clause
-- delete WITHOUT a where clause will delete EVERY ROW from the table (i.e. EVERYTHING)
delete from public.users where email like 'bmartino.%@%.com'; -- must contain bmartino. some characters @ some characters .com

-- select DQL
select * from public.users; -- * means all
select email from public.users; -- query projection (email) (only email column, all rows)
select email as username, password as secret from public.users;

-- Project 0
create table if not exists public.librarycatalog (
	id serial primary key,
	itemType char(1) not null,
	idNum numeric(10,0) not null unique,
	title VARCHAR(50) not null,
	author VARCHAR(50) not null,
	publisher VARCHAR(50) not null,
	itemYear numeric(4,0) not null,
	dictLanguage VARCHAR(30),
	dictWordCount numeric(10,0),
	novlGenre VARCHAR(30)
);

insert into public.librarycatalog (itemtype, idnum, title, author, publisher, itemyear, dictlanguage, dictwordcount, novlgenre) values 
	('D', '12345', 'TheBigBookofWords', 'ProfessorWright', 'Wordsmith', '2015', 'English', '9876', null),
	('N', '67890', 'TheGreatAdventure', 'ArthurEnglish', 'StorytimeLLC', '2009', null, null, 'Drama');

insert into public.librarycatalog (itemtype, idnum, title, author, publisher, itemyear, dictlanguage, dictwordcount, novlgenre) values 
	('N', '13579', 'MurderMysteryMan', 'DocMaroon', 'StorytimeLLC', '2011', null, null, 'Mystery'),
	('D', '24680', 'LarousseMini', 'MonsieurSeigneur', 'ViveLaLiterature', '2002', 'French', '7777', null);

delete from public.librarycatalog;

drop table public.librarycatalog;

-- WEDNESDAY SCRIPTS (4/15)

create schema flashcards;

create table if not exists flashcards.creator (
	id serial primary key,
	creator_name varchar(40) not null unique
);

create table if not exists flashcards.card (
	id serial primary key,
	question varchar(100) not null,
	creator_id int4 references flashcards.creator(id)
);

create  user flashcards_user with encrypted password 'p@$$w0rd123';
grant all privileges on database postgres to flashcards_user;
GRANT ALL ON SCHEMA flashcards TO flashcards_user;
GRANT ALL ON TABLE flashcards.card TO flashcards_user;
GRANT ALL ON TABLE flashcards.creator TO flashcards_user;


insert into flashcards.creator (creator_name) values ('bart');
insert into flashcards.creator (creator_name) values
	('john'), ('jane'), ('steve'), ('sara');
	
-- THURSDAY SCRIPTS (4/16)

create table if not exists users_thurs (
	id serial primary key,
	first_name varchar(30) not null,
	last_name varchar(30),
	email varchar(50) not null unique,
	password char(40) not null
);

create table if not exists posts (
	id serial primary key,
	author_id integer references users_thurs(id) not null,
	wall_user_id integer references users_thurs(id) not null,
	content varchar(500) not null
);

create table if not exists likes (
	user_id integer references users_thurs(id) not null,
	post_id integer references posts(id) not null,
	primary key(user_id, post_id)
);

insert into users_thurs (first_name, last_name, email, password) values
	('Hayden', 'Fields', 'haydenfields@revature.com', 'thisisabadpassword1');

insert into users_thurs (first_name, last_name, email, password) values
	('James', 'Douglas', 'jamesdouglas@revature.com', 'thisisabadpassword2'),
	('Jane', 'Samson', 'janesamson@revature.com', 'thisisabadpassword3');

select author_id, content, wall_user_id from posts;

-- joins
-- INNER, LEFT, RIGHT, CROSS, FULL (OUTER), NATURAL, SELF, UNEQUAL

-- cross join
select * from users_thurs, posts; -- cartesian product

-- inner join
select first_name as author, posts.id, content as posts from users_thurs
join posts on users_thurs.id = posts.author_id;

--left join
select users_thurs.id, first_name, last_name, count(*) as count_num
	from users_thurs
	left join posts on posts.author_id = users_thurs.id
	--where last_name = 'Fields'
	group by users_thurs.id
	--having users_thurs.id > 1
	order by count_num desc;
	
-- right join
-- in this example, our right join counts the records from the inner join
select count(*) from users_thurs 
	right join posts on users_thurs.id = posts.author_id;
	
-- select statement
select first_name, last_name from users_thurs where email='haydenfields@revature.com';
--select * from table where some=condition;
--       ^ Projection			   ^ Filter
--	       Where it will read		 What it will only allow 
--		   and what it will display

-- Indexes
-- Used for searching and reading data from a database
-- An index is a copy of the database table sorted by a certain column
-- This allows for a binary search algorithm to run on the table BTree
-- DBs automatically create indexes for PKs and unique columns
-- Using indexes will INCREASE the time it takes to update and delete from the database
--     (as every index will make a copy of the data, and updates/deletes are done on ALL copies)
create index on posts(author_id);
select * from posts where author_id=1;
drop index posts_author_id_idx;

delete from users_thurs;
delete from posts;
delete from likes;

drop table users_thurs;
drop table posts;
drop table likes;

-- FRIDAY SCRIPTS (4/17)

-- PL/SQL
-- Procedural Language for SQL
-- Postgres plpsql can be written in many different languages, including Java.
-- 	   But for this class, we'll focus on general PL/SQL
-- Stored procedures and functions
--     Postgres doesn't have stored procedures, only functions
-- Procedure is an operation that has no return (typically) (instead returns void)
-- Function is an operation or group of operations stored on the DB which can be recalled later.
--     They have access to DML/DQL commands
--                         name   parameter(s)
create or replace function my_sum(val_a INTEGER, val_b INTEGER)
returns INTEGER as $$ -- <-- body delimiter (don't ask questions, do this to be happy all the time
	begin 
		return val_a + val_b;	-- <-- function body (code block)
	end 
$$ language plpgsql;

-- invoke the function
select my_sum(2,3);	   -- example of a SCALAR function

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

-- to return a set of rows to a user, you must use a cursor
--     1 problem however: in DBeaver or PGAdmin, when you return a cursor from a function
--         the curosr is closed when the function finishes executing.
--		   In JDBC, when a function is finished working, the cursor is converted into a ResultSet once the cursor is returned from SQL to the JDBC
-- A cursor resembles an Iterator in terms of behavior, where it iterates through the set of rows 

-- Triggers
-- Some event condition that will execute some sql commands when met
create table if not exists colors (
	id serial primary key,
	color varchar(40)
);

insert into colors (color) values ('blue'), ('red'), ('green'), ('purple'), ('gold'), ('orange'), ('brown'), ('black'), ('silver'), ('magenta');

-- Event Types: Before, After, Instead
-- Event Operations: Insert, Update, delete

-- TG_OP: trigger operation
-- NEW: Incoming data for an insert or update
-- OLD: Data that already exists in the database
create or replace function no_blues()
returns trigger as $$
	begin 
		if(TG_OP = 'INSERT') then 
			if (UPPER(new.color) = 'BLUE') then  -- UPPER('blue) = 'BLUE'
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
	
insert into colors (color) values ('pink'); -- pink can be inserted many times
select * from colors;
insert into colors (color) values ('blue'); -- no change can be made as the trigger works
select * from colors;
update colors set color='blue' where color='gold'; -- gold moved to bottom, but gold is not changed to blue with trigger in action
select * from colors;

-- Sequences
-- Sequences look exactly like you expect it:
--     1,2,3,4,5,6...
-- That's it, sequences exist for the sake of counting in SQL. 
-- Sequences can keep track of anything in SQL that can be counted.
create sequence my_incr
	start 1000;
select nextval('my_incr'); -- every call increments a sequence by one (1000, 1001, 1002, 1003, 1004, ...)

create sequence my_incr_1
	start 1000
	increment by 2;
select nextval('my_incr_1'); -- 1002, 1004, 1006, ...

create sequence my_incr_2
	start 1000
	minvalue 1000
	maxvalue 1010
	cycle
	increment by 2;
select nextval('my_incr_2'); -- 1002, 1004, 1006, 1008, 1010, 1000, 1002, ...

create table test_table (
	id integer primary key,
	val boolean
);

insert into test_table (id, val) values (nextval('my_incr_2'), true), (nextval('my_incr_2'), false), (nextval('my_incr_2'), false);
select * from test_table;
