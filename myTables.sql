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

create table if not exists project_0.tansactions(
	username VARCHAR(20) not null,
	action VARCHAR(20) not null,
	cur_timestamp TIMESTAMP(8)
);
