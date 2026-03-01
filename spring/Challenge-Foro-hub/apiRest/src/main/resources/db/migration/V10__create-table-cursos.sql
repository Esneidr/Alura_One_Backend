create table topicos(
    id bigint generated always as identity primary key,
    autor varchar(100) not null,
    titulo varchar(100) not null unique,
    mensaje varchar(2000) not null unique,
    curso varchar(20) not null
);