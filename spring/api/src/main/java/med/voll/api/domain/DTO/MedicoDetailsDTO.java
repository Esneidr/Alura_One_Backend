package med.voll.api.domain.DTO;

import med.voll.api.domain.Enum.Especialidad;
import med.voll.api.domain.model.Direccion;
import med.voll.api.domain.model.Medico;

public record MedicoDetailsDTO(
        Long id,
        String nombre,
        String email,
        String documento,
        String telefono,
        Especialidad especialidad,
        Direccion direccion
) {
    public MedicoDetailsDTO(Medico medico) {
        this(
                medico.getId(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getDocumento(),
                medico.getTelefono(),
                medico.getEspecialidad(),
                medico.getDireccion()
        );
    }
}
