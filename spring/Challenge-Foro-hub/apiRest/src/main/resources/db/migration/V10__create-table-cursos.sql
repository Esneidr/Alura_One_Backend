create table cursos(
    id bigint generated always as identity primary key,
    nombre varchar(100) not null unique,
    categoria varchar(50) not null
);

INSERT INTO cursos (nombre, categoria)
VALUES ('Curso General', 'ARCHITECTURE');