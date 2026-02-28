-- 1. Agregar la nueva columna (permite NULL para no romper registros existentes)
alter table medicos
add telefono varchar(10);

-- 2. Asignar un valor por defecto a los registros antiguos
update medicos
set telefono = '0000000000'
where telefono is null;

-- 3. Aplicar la restricción NOT NULL al campo telefono
alter table medicos
alter column telefono set not null;