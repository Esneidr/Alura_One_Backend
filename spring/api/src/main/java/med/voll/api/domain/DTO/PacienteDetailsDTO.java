package med.voll.api.domain.DTO;

import med.voll.api.domain.model.Direccion;
import med.voll.api.domain.model.Paciente;

public record PacienteDetailsDTO(
        Long id,
        String nombre,
        String email,
        String documento,
        String telefono,
        Direccion direccion
) {
    public PacienteDetailsDTO(Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail(),
                paciente.getDocumento(),
                paciente.getTelefono(),
                paciente.getDireccion()
        );
    }
}
