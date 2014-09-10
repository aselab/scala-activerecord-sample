# Tasks schema

# --- !Ups
insert into users(name, login, email, hashed_password, group_id, created_on, updated_on)
values ('user1', 'user1', 'user1@foo.com', '5f4dcc3b5aa765d61d8327deb882cf99', 1, '2014-01-01', '2014-01-24');

# --- !Downs
delete from users;
