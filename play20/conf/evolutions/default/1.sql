# Tasks schema

# --- !Ups
create table User (
  name varchar(128) not null,
  description varchar(3000),
  age int not null,
  id bigint not null primary key auto_increment
);

# --- !Downs
drop table User;
