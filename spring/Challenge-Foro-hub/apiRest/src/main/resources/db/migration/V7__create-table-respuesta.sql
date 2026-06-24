CREATE TABLE repuestas (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    mensaje VARCHAR(2000) NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_id BIGINT NOT NULL,
    topico_id BIGINT NOT NULL,
    solucion BOOLEAN NOT NULL DEFAULT FALSE,
    activa BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT fk_repuestas_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuarios(id)
        ON DELETE RESTRICT,

   CONSTRAINT fk_repuestas_topico
        FOREIGN KEY (topico_id)
        REFERENCES topicos(id)
        ON DELETE CASCADE
);