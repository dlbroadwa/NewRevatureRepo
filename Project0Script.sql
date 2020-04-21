create schema project0library;

create table if not exists project0library.itemcatalog (
	id serial primary key,
	itemType char(1) not null,
	idNum numeric(10,0) not null unique,
	checkstatus bit not null,
	title VARCHAR(50) not null,
	author VARCHAR(50) not null,
	publisher VARCHAR(50) not null,
	itemYear numeric(4,0) not null,
	dictLanguage VARCHAR(30),
	dictWordCount numeric(10,0),
	novlGenre VARCHAR(30)
);

insert into project0library.itemcatalog (itemtype, idnum, checkstatus, title, author, publisher, itemyear, dictlanguage, dictwordcount, novlgenre) values 
	('D', '12345', '1', 'TheBigBookofWords', 'ProfessorWright', 'Wordsmith', '2015', 'English', '9876', null),
	('N', '67890', '1', 'TheGreatAdventure', 'ArthurEnglish', 'StorytimeLLC', '2009', null, null, 'Drama');

insert into project0library.itemcatalog (itemtype, idnum, checkstatus, title, author, publisher, itemyear, dictlanguage, dictwordcount, novlgenre) values 
	('N', '13579', '1', 'MurderMysteryMan', 'DocMaroon', 'StorytimeLLC', '2011', null, null, 'Mystery'),
	('D', '24680', '1', 'LarousseMini', 'MonsieurSeigneur', 'ViveLaLiterature', '2002', 'French', '7777', null);

--delete from project0library.itemcatalog;
--
--drop table project0library.itemcatalog;

create user library_admin with encrypted password 'thisIsABadPassword!';
grant all privileges on database postgres to library_admin;
GRANT ALL ON SCHEMA project0library TO library_admin;
GRANT ALL ON TABLE project0library.itemcatalog TO library_admin;
grant usage, select on all sequences in schema project0library to library_admin;

update project0library.itemcatalog set checkstatus=cast(0 as bit) where idnum=12345;

select * from project0library.itemcatalog where idnum=12345;

select * from project0library.itemcatalog order by idnum;

delete from project0library.itemcatalog where idnum=12345;