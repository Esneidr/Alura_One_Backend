-- 1️⃣ Agregar la columna permitiendo NULL
ALTER TABLE topicos
ADD COLUMN estado VARCHAR(20);

-- 2️⃣ Poblar registros existentes
UPDATE topicos
SET estado = 'ABIERTO'
WHERE estado IS NULL;

-- 3️⃣ Establecer valor por defecto para nuevos registros
ALTER TABLE topicos
ALTER COLUMN estado SET DEFAULT 'ABIERTO';

-- 4️⃣ Bloquear NULL definitivamente
ALTER TABLE topicos
ALTER COLUMN estado SET NOT NULL;