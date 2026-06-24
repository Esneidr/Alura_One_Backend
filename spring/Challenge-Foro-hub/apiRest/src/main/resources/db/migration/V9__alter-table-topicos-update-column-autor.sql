-- 1️⃣ Agregar la columna permitiendo NULL
ALTER TABLE topicos
ADD COLUMN autor_id BIGINT;

UPDATE topicos t
SET autor_id = u.id
FROM usuarios u
WHERE t.autor = u.nombre_completo;

ALTER TABLE topicos
ALTER COLUMN autor_id SET NOT NULL;

ALTER TABLE topicos
ADD CONSTRAINT fk_topicos_autor
FOREIGN KEY (autor_id)
REFERENCES usuarios(id)
ON DELETE RESTRICT;

ALTER TABLE topicos
DROP COLUMN autor;