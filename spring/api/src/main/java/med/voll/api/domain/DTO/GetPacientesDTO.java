package med.voll.api.domain.DTO;

import med.voll.api.domain.model.Paciente;

public record GetPacientesDTO(
        Long id,
        String nombre,
        String email,
        String documento,
        String telefono
) {
    public GetPacientesDTO(Paciente data) {
        this(
                data.getId(),
                data.getNombre(),
                data.getEmail(),
                data.getDocumento(),
                data.getTelefono()
        );
    }
}
