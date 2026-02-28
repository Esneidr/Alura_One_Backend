package med.voll.api.domain.repository;


import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByMedicoIdAndFechaAndMotivoIsNull(Long idMedico, LocalDateTime facha);

    boolean existsByPacienteIdAndFechaBetween(Long idPaciente, LocalDateTime primerHorario, LocalDateTime ultimoHorario);
}
