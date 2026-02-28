package med.voll.api.domain.validaciones.reserva;

import med.voll.api.domain.DTO.ConsultaDTO;
import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoSinReservas implements ValidadorConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(ConsultaDTO data) {
        var medico = repository.existsByMedicoIdAndFechaAndMotivoIsNull(data.idMedico(), data.fecha());
        if (medico) {
            throw new ValidacionException(
                    "El médico seleccionado no está disponible en la fecha y hora indicadas."
            );
        }
    }
}
