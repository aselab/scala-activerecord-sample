# Tasks schema

# --- !Ups
create table magazines (
  name varchar(128) not null,
  id bigint not null primary key auto_increment
);

create table ads (
  name varchar(128) not null,
  price int not null,
  magazine_id bigint not null,
  id bigint not null primary key auto_increment
);
