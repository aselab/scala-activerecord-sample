# Tasks schema

# --- !Ups
create table groups (
  name varchar(128) not null,
  id bigint not null primary key auto_increment
);

# --- !Downs
drop table groups;
