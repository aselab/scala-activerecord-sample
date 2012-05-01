# Tasks schema

# --- !Ups
insert into User (name, age, description)
values ('user1', 18, 'hogehoge');

insert into User (name, age, description)
values ('user2', 36, null);

# --- !Downs
delete from User;
