# Tasks schema

# --- !Ups
insert into groups(name)
values ('group1');

insert into groups(name)
values ('group2');

insert into groups(name)
values ('group3');

# --- !Downs
delete from groups;
