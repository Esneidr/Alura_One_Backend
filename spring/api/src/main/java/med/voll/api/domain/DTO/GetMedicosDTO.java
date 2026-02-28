package med.voll.api.domain.DTO;

import med.voll.api.domain.Enum.Especialidad;
import med.voll.api.domain.model.Medico;

public record GetMedicosDTO(
        Long id,
        String nombre,
        String email,
        String documento,
        Especialidad especialidad
) {
    public GetMedicosDTO(Medico medico) {
        this(
                medico.getId(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getDocumento(),
                medico.getEspecialidad()
        );
    }
}
