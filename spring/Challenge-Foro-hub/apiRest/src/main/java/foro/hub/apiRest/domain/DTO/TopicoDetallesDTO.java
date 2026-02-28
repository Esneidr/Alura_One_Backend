package foro.hub.apiRest.domain.DTO;

import foro.hub.apiRest.domain.ENUM.Curso;
import foro.hub.apiRest.domain.model.Topico;

import java.time.LocalDateTime;

public record TopicoDetallesDTO(
        Long id,
        String autor,
        String titulo,
        String mensaje,
        Curso curso,
        LocalDateTime fechaCreacion,
        Boolean activo
) {
    public TopicoDetallesDTO(Topico topico) {
        this(
                topico.getId(),
                topico.getAutor(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getCurso(),
                topico.getFechaCreacion(),
                topico.getActivo()
        );
    }
}
