package foro.hub.apiRest.domain.DTO;

import foro.hub.apiRest.domain.ENUM.Curso;
import jakarta.validation.constraints.NotNull;

public record PutTopico(
        @NotNull Long id,
        String autor,
        String titulo,
        String mensaje,
        Curso curso
) {
}
