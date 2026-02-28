package med.voll.api.domain.validaciones.reserva;

import med.voll.api.domain.DTO.ConsultaDTO;
import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacientesActivos implements ValidadorConsulta {

    @Autowired
    private PacienteRepository repository;

    public void validar(ConsultaDTO data) {
        var pacienteActivo = repository.findActivoById(data.idPaciente());

        if (!pacienteActivo) {
            throw new ValidacionException(
                    "El paciente no se encuentra activo en el sistema."
            );
        }
    }
}
