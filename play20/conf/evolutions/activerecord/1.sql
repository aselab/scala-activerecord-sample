# Tasks schema

# --- !Ups
create table User (
  name varchar(128) not null,
  login varchar(128) not null,
  email varchar(128),
  hashedPassword varchar(128),
  id bigint not null primary key auto_increment
);

# --- !Downs
drop table User;
