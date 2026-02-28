package med.voll.api.domain.validaciones.reserva;

import med.voll.api.domain.DTO.ConsultaDTO;
import med.voll.api.domain.ValidacionException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorConsultaAnticipacion implements ValidadorConsulta {

    public void validar(ConsultaDTO data) {
        var fecha = data.fecha();

        var ahora = LocalDateTime.now();
        var diferenciaMinutos = Duration.between(ahora, fecha).toMinutes();

        if (diferenciaMinutos < 30) {
            throw  new ValidacionException(
                    "La consulta debe agendarse con al menos 30 minutos de anticipación."
            );
        }
    }
}
