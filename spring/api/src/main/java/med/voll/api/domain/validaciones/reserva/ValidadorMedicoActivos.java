package med.voll.api.domain.validaciones.reserva;

import med.voll.api.domain.DTO.ConsultaDTO;
import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoActivos implements ValidadorConsulta {

    @Autowired
    private MedicoRepository repository;

    public void validar(ConsultaDTO data) {
        if (data.idMedico() == null) {
            return;
        }

        var activo = repository.findActivoById(data.idMedico());
        if(!activo) {
            throw new ValidacionException(
                    "El medico no se encuentra activo en el sistema."
            );
        }
    }
}
