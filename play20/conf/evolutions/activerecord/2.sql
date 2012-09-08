# Tasks schema

# --- !Ups
insert into User (name, login, email, hashedPassword)
values ('user1', 'user1', 'user1@foo.com', '5f4dcc3b5aa765d61d8327deb882cf99');

# --- !Downs
delete from User;
