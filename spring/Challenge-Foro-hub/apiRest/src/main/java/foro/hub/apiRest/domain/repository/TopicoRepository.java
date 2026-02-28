package foro.hub.apiRest.domain.repository;

import foro.hub.apiRest.domain.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    List<Topico> findByActivoTrueOrderByFechaCreacionDesc();

    boolean existsByTituloOrMensajeAndIdNot(String titulo, String mensaje, Long id);

    boolean existsByMensaje(String mensaje);

    boolean existsByTitulo(String titulo);
}
