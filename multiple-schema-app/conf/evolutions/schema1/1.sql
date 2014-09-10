# Tasks schema

# --- !Ups
create table users (
  name varchar(128) not null,
  login varchar(128) not null,
  email varchar(128),
  hashed_password varchar(128),
  group_id bigint,
  created_on date,
  updated_on date,
  id bigint not null primary key auto_increment
);

# --- !Downs
drop table users;
