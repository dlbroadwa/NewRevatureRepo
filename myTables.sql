create schema project_0;

-- create DDL
create table if not exists project_0.animal_inventory (
	name VARCHAR(15) not null,
	type VARCHAR(20) not null,
	gender VARCHAR(1) not null,
	age int,
	enclosure int
);

insert into project_0.animal_inventory (name,type,gender,age,enclosure) values
('Sue','Tiger','F','5','2'),
('Bingo','Hippo','M','3','5'),
('Pinky','Flamingo','F','1','1'),
('Tigger','Tiger','M','2','2'),
('Ele','Elephant','F','13','3'),
('Abe','Ape','M','2','4'),
('Fred','Flamingo','M','2','1'),
('Sam','Gorilla','M','8','9'),
('Kate','Gorilla','F','7','9'),
('Elon', 'Elephant','M','14','3'),
('Singo','Hippo','F','2','5'),
('Alice','Ape','F','2','4');

create table if not exists project_0.keepers(
	firstname VARCHAR(15) not null,
	lastname VARCHAR(20)not null,
	username VARCHAR(20)not null unique,
	password VARCHAR(20)not null
);

insert into project_0.keepers (firstname,lastname,username,password) values
('Paityn','Maynard','paitynm','Revature'),
('August','Duet','augustd','Revature'),
('Keeper','Man','user','password');

select * from project_0.animal_inventory order by type;

delete from project_0.animal_inventory where name='Gally' and type='Alligator';

-- You should know how to alter a table
-- alter table

-- insert DML
-- be sure to use single quotes for values
--insert into public.users (password , email) values ('0123456789012345678901234567890123456789','paityn.maynard@gmail.com');
--insert into public.users (password , email) values
--('0123456789012345678901234567890123456789','janed@gmail.com'),
--('0123456789012345678901234567890123456789','barv@gmail.com'),
--('0123456789012345678901234567890123456789','pad@gmail.com');

--update/delete DML
--updates typically have a where clause
--updates w/o where clause will attempt to change the data for all rows
--update public.users set email = 'paityn.maynard@gmail.com' where id=1 and email = 'paitynmaynard@gmail.com';

-- deletes typically have a where clause
--deletes w/o a where clause will delete every row from the table
--delete from public.users where email like 'paityn.%@%' ;

--select DQL
--select * from public.users;
--select email as username, "password" as secreat from public.users;






