package med.voll.api.domain.validaciones.cancelamiento;

import med.voll.api.domain.DTO.CancelarDTO;

public interface ValidadorCancelacionConsulta {
    void validar(CancelarDTO data);
}
