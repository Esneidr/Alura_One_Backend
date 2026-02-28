package med.voll.api.domain.service;

import med.voll.api.domain.DTO.CancelarDTO;
import med.voll.api.domain.DTO.ConsultaDTO;
import med.voll.api.domain.DTO.ConsultaDetailsDTO;
import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.model.Consulta;
import med.voll.api.domain.model.Medico;
import med.voll.api.domain.repository.ConsultaRepository;
import med.voll.api.domain.repository.MedicoRepository;
import med.voll.api.domain.repository.PacienteRepository;
import med.voll.api.domain.validaciones.cancelamiento.ValidadorCancelacionConsulta;
import med.voll.api.domain.validaciones.reserva.ValidadorConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorConsulta> validadores;

    @Autowired
    private List<ValidadorCancelacionConsulta> cancelacionConsultas;

    public void cancelar(CancelarDTO data) {
        if (!repository.existsById(data.id())) {
            throw new ValidacionException("La consulta no existe");
        }

        cancelacionConsultas.forEach(c -> c.validar(data));

        var consulta = repository.getReferenceById(data.id());
        consulta.cancelar(data.motivo());
    }

    public ConsultaDetailsDTO reservar(ConsultaDTO data) {
        if (!pacienteRepository.existsById(data.idPaciente())) {
            throw new ValidacionException("El paciente no existe");
        }

        if (data.idMedico() != null && !medicoRepository.existsById(data.idMedico())) {
            throw new ValidacionException("El médico no existe.");
        }

        //Validaciones
        validadores.forEach(v -> v.validar(data));
        if (data.idMedico() == null ) {
            throw  new ValidacionException("El médico no existe en ese horario.");
        }
        var medico = elegirMedico(data);
        var paciente = pacienteRepository.findById(data.idPaciente()).get();
        var consulta = new Consulta(null, medico, paciente, data.fecha(), null);

        repository.save(consulta);
        return new ConsultaDetailsDTO(consulta);
    }

    private Medico elegirMedico(ConsultaDTO data) {
        if (data.idMedico() != null) {
            return medicoRepository.getReferenceById(data.idMedico());
        }

        if (data.especialidad() == null) {
            throw new ValidacionException(
                    "Debe seleccionar una especialidad cuando no se elige un médico."
            );
        }
        return medicoRepository.elegirMedicoAleatorio(data.especialidad(), data.fecha());
    }


}
