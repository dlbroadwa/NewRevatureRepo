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

--create table if not exists project0library.admins (
--	id serial primary key,
--	email VARCHAR(30) not null unique,
--	password char(40) not null 
--);
--
--insert into project0library.admins (password, email) values ('thisIsABadPassword!', 'bookerdwight@library.com');
--
--delete from project0library.admins;
--
--drop table project0library.admins;
--
--select email as username, password as secret from project0library.admins;

create user library_admin with encrypted password 'thisIsABadPassword!';
grant all privileges on database postgres to library_admin;
GRANT ALL ON SCHEMA project0library TO library_admin;
GRANT ALL ON TABLE project0library.itemcatalog TO library_admin;

update project0library.itemcatalog set checkstatus=cast(0 as bit) where idnum=12345; 

--create table if not exists project0library.novelinfo (
--	id serial primary key,
--	novlGenre VARCHAR(30) not null
--);
--
--create table if not exists project0library.dictinfo (
--	id serial primary key,
--	dictLanguage VARCHAR(30) not null,
--	dictWordCount numeric(10,0) not null
--);
--
--create table if not exists project0library.baseinfo (
--	id serial primary key,
--	title VARCHAR(50) not null,
--	author VARCHAR(50) not null,
--	publisher VARCHAR(50) not null,
--	itemYear numeric(4,0) not null
--	-- dictinfo OR novelinfo? dictinfo AND novelinfo?
--);
--
--create table if not exists project0library.item (
--	id serial primary key,
--	itemType char(1) not null,
--	base_info int4 references project0library.baseinfo(id)
--);
--
--create table if not exists project0library.librarycatalog (
--	id serial primary key,
--	idNum numeric(10,0) not null unique,
--	item int4 references project0library.item(id)
--);

--create TYPE novelinfo AS OBJECT (
--	genre VARCHAR(30) not null
--);
--
--create type dictinfo as object (
--	lang VARCHAR(30) not null,
--	wordCnt numeric(10,0) not null
--);
--
--create type extrainfo as table of novelinfo and dictinfo;
--
--create table if not exists project0library.catalogItems (
--	id serial primary key,
--	itemType char(1) not null,
--	idNum numeric(10,0) not null unique,
--	title VARCHAR(50) not null,
--	author VARCHAR(50) not null,
--	publisher VARCHAR(50) not null,
--	itemYear year not null,
--	exInfo extrainfo
--);


