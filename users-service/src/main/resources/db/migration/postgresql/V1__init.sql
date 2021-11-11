create sequence pk_users_sequence start 1 increment 1;

create table users (
    id int4 not null,
    user_id uuid not null,
    type varchar(30) not null,
    username varchar(150) not null,
    password varchar(60) not null,
    email varchar(150) not null,
    email_verified boolean not null,
    first_name varchar (150),
    last_name varchar (150),
    age int4,
    access_token text,
    refresh_token text,
    created timestamp not null default current_timestamp,
    updated timestamp not null default current_timestamp,
    primary key (id)
);

alter table if exists users
   add constraint user_id_and_username_unique unique (user_id, username);
