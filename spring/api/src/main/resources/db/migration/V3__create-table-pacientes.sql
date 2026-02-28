create table pacientes(
    id bigint generated always as identity primary key,
    nombre varchar(100) not null,
    email varchar(100) not null unique,
    documento varchar(10) not null unique,
    telefono varchar(10) not null,
    calle varchar(50) not null,
    complemento varchar(50),
    numero varchar(20),
    barrio varchar(100) not null,
    codigo_postal varchar(6) not null,
    estado varchar(100) not null,
    ciudad varchar(100) not null
);