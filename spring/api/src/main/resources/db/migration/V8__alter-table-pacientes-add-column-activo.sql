-- 1. Agregar la nueva columna (permite NULL para no romper registros existentes)
ALTER TABLE pacientes
ADD COLUMN activo BOOLEAN;

-- 2. Asignar valor por defecto a los registros antiguos
UPDATE pacientes
SET activo = true
WHERE activo IS NULL;

-- 3. Aplicar restricciones
ALTER TABLE pacientes
ALTER COLUMN activo SET NOT NULL,
ALTER COLUMN activo SET DEFAULT true;
