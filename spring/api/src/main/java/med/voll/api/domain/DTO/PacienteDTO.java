package med.voll.api.domain.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PacienteDTO(
        @NotBlank String nombre,
        @NotBlank @Email String email,
        @NotBlank @Pattern(regexp = "\\d{8,10}") String documento,
        @NotBlank @Pattern(regexp ="\\d{10}") String telefono,
        @NotNull @Valid DireccionDTO direccion
) {
}
