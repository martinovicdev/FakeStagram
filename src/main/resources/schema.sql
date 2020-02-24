create table if not exists users (
  username varchar(20) not null primary key,
  password varchar(100) not null,
  enabled boolean not null,
  permission varchar(100) not null
);

create table if not exists authorities (
  username varchar(20) not null,
  authority varchar(20) not null,
  constraint fk_authorities_users foreign key(username) references users(username)
);

create table if not exists image (
	id identity,
	name varchar(20) not null,
	fakeness decimal(20, 2) not null,
	type varchar(20) not null,
	date date not null,
	path varchar(300) not null,
	owner varchar(50) not null
);