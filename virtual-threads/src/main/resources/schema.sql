create table if not exists "users"
(
    id      serial primary key,
    name    varchar(255),
    surname varchar(255)
);

create table if not exists "normalized_users"
(
    id      serial primary key,
    name    varchar(255),
    surname varchar(255)
);