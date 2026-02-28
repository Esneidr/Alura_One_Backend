package med.voll.api.domain.DTO;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.Enum.Motivo;

public record CancelarDTO(
        @NotNull Long id,
        @NotNull Motivo motivo
) {
}
