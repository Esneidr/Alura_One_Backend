create table medicos(
    id bigint generated always as identity primary key,
    nombre varchar(100) not null,
    email varchar(100) not null unique,
    documento varchar(100) not null unique,
    especialidad varchar(100) not null,
    calle varchar(100) not null,
    complemento varchar(100),
    numero varchar(20),
    barrio varchar(100) not null,
    codigo_postal varchar(12) not null,
    estado varchar(100) not null,
    ciudad varchar(100) not null
);
