package med.voll.api.domain.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.Enum.Especialidad;

public record MedicoDTO(
        @NotBlank String nombre,
        @NotBlank @Email String email,
        @NotBlank @Pattern(regexp = "\\d{8,10}") String documento,
        @NotBlank @Pattern(regexp ="\\d{10}") String telefono,
        @NotNull Especialidad especialidad,
        @NotNull @Valid DireccionDTO direccion
) {
}
