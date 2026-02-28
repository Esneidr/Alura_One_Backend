-- 1. Agregar la nueva columna (permite NULL para no romper registros existentes)
alter table topicos
add column activo BOOLEAN;

-- 2. Asignar un valor por defecto a los registros antiguos
update topicos
set activo = true
where activo is null;

-- 3. Aplicar la restricción NOT NULL al campo telefono
alter table topicos
alter column activo set not null;