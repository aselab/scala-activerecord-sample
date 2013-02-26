# Tasks schema

# --- !Ups
create table users (
  name varchar(128) not null,
  login varchar(128) not null,
  email varchar(128),
  hashed_password varchar(128),
  id bigint not null primary key auto_increment
);

# --- !Downs
drop table users;
