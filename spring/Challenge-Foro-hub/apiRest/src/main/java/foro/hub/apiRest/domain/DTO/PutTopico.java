package foro.hub.apiRest.domain.DTO;

import foro.hub.apiRest.domain.ENUM.Estado;
import foro.hub.apiRest.domain.model.Curso;
import foro.hub.apiRest.domain.model.Usuario;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PutTopico(
        @NotNull Long id,
        Usuario autor,
        String titulo,
        String mensaje,
        Curso curso,
        LocalDateTime fechaActualizacion,
        Estado estado,
        Boolean activo
) {
}
