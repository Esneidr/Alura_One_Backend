package foro.hub.apiRest.domain.DTO;

import foro.hub.apiRest.domain.model.Topico;

import java.time.LocalDateTime;

public record TopicoDetallesDTO(
        Long id,
        Long autor,
        String titulo,
        String mensaje,
        Long curso,
        LocalDateTime fechaCreacion,
        Boolean activo
) {
    public TopicoDetallesDTO(Topico topico) {
        this(
                topico.getId(),
                topico.getAutor().getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getCurso().getId(),
                topico.getFechaCreacion(),
                topico.getActivo()
        );
    }
}
