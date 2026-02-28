-- 1. Agregar la nueva columna (permite NULL para no romper registros existentes)
alter table topicos
add fecha_creacion TIMESTAMP;

-- 2. Asignar un valor por defecto a los registros antiguos
update topicos
set fecha_creacion = '2026-02-23 00:00:00'
where fecha_creacion is null;

-- 3. Aplicar la restricción NOT NULL al campo telefono
alter table topicos
alter column fecha_creacion set not null;