package med.voll.api.domain.validaciones.reserva;

import med.voll.api.domain.DTO.ConsultaDTO;
import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorConsultaMismoDia implements ValidadorConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(ConsultaDTO data) {
        var primerHorario = data.fecha().withHour(7);
        var ultimoHorario = data.fecha().withHour(18);

        var paciente = repository.existsByPacienteIdAndFechaBetween(data.idPaciente(), primerHorario, ultimoHorario);
        if (paciente) {
            throw new ValidacionException(
                    "El paciente ya cuenta con una consulta reservada para ese día."
            );
        }

    }
}
