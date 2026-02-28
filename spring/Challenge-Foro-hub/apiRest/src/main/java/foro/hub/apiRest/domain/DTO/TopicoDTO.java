package foro.hub.apiRest.domain.DTO;

import foro.hub.apiRest.domain.ENUM.Curso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TopicoDTO(
        @NotBlank (message = "El autor no puede estar vacío")
        String autor,

        @NotBlank (message = "El título no puede estar vacío")
        @Size(min = 5, max = 100, message = "El título debe tener entre 5 y 100 caracteres")
        String titulo,

        @NotBlank (message = "El mensaje no puede estar vacío")
        @Size(min = 10, max = 2000, message = "El mensaje debe tener entre 10 y 2000 caracteres")
        String mensaje,

        @NotNull (message = "El curso es obligatorio")
        Curso curso

        ) {
}
