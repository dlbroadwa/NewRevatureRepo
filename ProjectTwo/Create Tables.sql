create table if not exists Addresses (
	addressID serial primary key,
	number integer not null,
	street varchar(50) not null,
	address2 varchar(50),
	city varchar(50) not null,
	state varchar(2) not null,
	country varchar(50) not null,
	zipcode numeric(5) not null
);

create table if not exists PhoneCarriers (
	phoneCarrierID serial primary key,
	phoneCarrier varchar(8) not null
);

create table if not exists "Users" (
	email varchar(30) primary key,
	firstname varchar(30) not null,
	lastname varchar(30) not null,
	phone numeric(11),
	phoneCarrierID integer not null references PhoneCarriers(phoneCarrierID),
	addressID integer not null references Addresses(addressID),
	experiencePoints integer not null
);

create table if not exists DifficultyLevels (
	difficultyLevelID serial primary key,
	difficultyLevel varchar(30)
);

create table if not exists Items (
	itemID serial primary key,
	itemName varchar(50) not null,
	description varchar(100) not null,
	quantity integer not null,
	image bytea not null,
	GPSLocation varchar(150) not null,
	difficultyLevel integer not null references DifficultyLevels(difficultyLevelID)
);

create table if not exists ItemHistorys (
	email varchar(30) not null references Users(email),
	itemID integer not null references Items(itemid),
	dateCollected timestamp not null,
	"comment" varchar(300) not null,
	rating smallint not null,
	primary key (email, itemid)
);