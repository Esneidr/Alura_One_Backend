package med.voll.api.domain.DTO;

import med.voll.api.domain.model.Consulta;

import java.time.LocalDateTime;

public record ConsultaDetailsDTO(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime fecha
) {
    public ConsultaDetailsDTO(Consulta consulta) {
        this(
                consulta.getId(),
                consulta.getMedico().getId(),
                consulta.getPaciente().getId(),
                consulta.getFecha()
        );
    }
}
