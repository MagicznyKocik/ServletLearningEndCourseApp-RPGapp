create database servlet_app_db;
use servlet_app_db;
create table user (
user_id int not null primary key unique auto_increment,
username varchar(45) not null unique,
email varchar(50) not null unique,
is_active boolean not null,
password varchar(45) not null
);

create table idea (
idea_id int  not null unique auto_increment,
name varchar(100) not null,
description varchar(250) not null,
url varchar(200)  not null unique,
user_id int not null,
date timestamp not null,
up_vote int not null,
down_vote int not null,
foreign key (user_id) references user(user_id),
primary key (idea_id, user_id)
);

create table vote (
vote_id int not null,
idea_id int not null,
user_id int not null,
date timestamp not null,
type varchar(30) not null,
foreign key (idea_id) references idea (idea_id),
foreign key (user_id) references user (user_id),
primary key (vote_id, idea_id, user_id)
);

create table role (
role_name varchar(45) primary key not null unique,
description varchar(120)
);

create table user_role (
role_name varchar(45) not null,
username varchar(45) not null,
foreign key (role_name) references role(role_name),
foreign key (username) references user(username),
primary key (role_name, username)
);