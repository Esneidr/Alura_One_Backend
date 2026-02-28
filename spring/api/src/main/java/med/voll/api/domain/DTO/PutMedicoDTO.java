package med.voll.api.domain.DTO;

import jakarta.validation.constraints.NotNull;

public record PutMedicoDTO(
        @NotNull Long id,
        String nombre,
        String telefono,
        DireccionDTO direccion
) {
}
