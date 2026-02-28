package med.voll.api.domain.validaciones.cancelamiento;

import med.voll.api.domain.DTO.CancelarDTO;
import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class CancelarHorarioConAnticipacion implements ValidadorCancelacionConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(CancelarDTO data) {
        var consulta = repository.getReferenceById(data.id());
        var ahora = LocalDateTime.now();
        var difrenciaHora = Duration.between(ahora, consulta.getFecha()).toHours();

        if (difrenciaHora < 24) {
            throw new ValidacionException(
                    "¡La consulta soo se puede ser cancelada con anticipación mínima de 24 horas!"
            );
        }

    }
}
