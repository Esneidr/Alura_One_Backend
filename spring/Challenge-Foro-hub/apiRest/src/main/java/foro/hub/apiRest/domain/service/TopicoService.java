package foro.hub.apiRest.domain.service;

import foro.hub.apiRest.domain.DTO.PutTopico;
import foro.hub.apiRest.domain.DTO.TopicoDTO;
import foro.hub.apiRest.domain.DTO.TopicoDetallesDTO;
import foro.hub.apiRest.infra.GestorErrores.ValidacionException;
import foro.hub.apiRest.domain.model.Topico;
import foro.hub.apiRest.domain.repository.TopicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository repository;

    public TopicoDetallesDTO obtenerUnTopico(Long id) {
        var topico = obtenerTopicoPorId(id);

        return new TopicoDetallesDTO(topico);
    }

    public List<TopicoDetallesDTO> obtenerTodosTopicos() {
        return repository.findByActivoTrueOrderByFechaCreacionDesc()
                .stream()
                .map(TopicoDetallesDTO::new)
                .toList();
    }

    @Transactional
    public Topico crearTopico(TopicoDTO data) {
        validarDuplicados(data.titulo(), data.mensaje(), null);

        var topico = new Topico(data);
        return repository.save(topico);
    }

    @Transactional
    public Topico actualizarTopico(PutTopico data) {

        var topico = obtenerTopicoPorId(data.id());

        validarDuplicados(data.titulo(), data.mensaje(), data.id());

        topico.actualizarTopico(data);
        return topico;
    }

    @Transactional
    public Topico cambiarActivo(Long id, boolean activar) {
        var topico = obtenerTopicoPorId(id);

        if (activar) {
            topico.activar();
        } else {
            topico.inactivar();
        }

        return topico;
    }

    //Metodo reutizable
    private void validarDuplicados(String titulo, String mensaje, Long id) {
        if (titulo != null) {
            boolean existe = (id == null)
                    ? repository.existsByTitulo(titulo)
                    : repository.existsByTituloOrMensajeAndIdNot (titulo, mensaje, id);

            if (existe) {
                throw new ValidacionException("Ya existe otro tópico con ese título.");
            }
        }

        if (mensaje != null) {
            boolean existe = (id == null)
                    ? repository.existsByMensaje(mensaje)
                    : repository.existsByTituloOrMensajeAndIdNot(titulo, mensaje, id);

            if (existe) {
                throw new ValidacionException("Ya existe otro tópico con ese mensaje.");
            }
        }
    }

    private Topico obtenerTopicoPorId(Long id) {
         return repository.findById(id)
                .orElseThrow(() ->
                        new ValidacionException("El tópico solicitado no existe.")
                );
    }
}
