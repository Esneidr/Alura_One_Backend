package med.voll.api.domain.validaciones.reserva;

import med.voll.api.domain.DTO.ConsultaDTO;
import med.voll.api.domain.ValidacionException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioConsulta implements ValidadorConsulta {

    public void validar(ConsultaDTO data) {
        var fecha = data.fecha();

        var esDomingo = fecha.getDayOfWeek() == DayOfWeek.SUNDAY;
        var antesDeHorario = fecha.getHour() < 7;
        var despuesDeHorario = fecha.getHour() > 18;

        if (esDomingo || antesDeHorario || despuesDeHorario) {
            throw new ValidacionException(
                    "Las consultas solo pueden agendarse de lunes a sábado, entre las 07:00 y las 18:00."
            );
        }
    }
}
