package foro.hub.apiRest.controller;

import foro.hub.apiRest.domain.DTO.ApiResponse;
import foro.hub.apiRest.domain.DTO.PutTopico;
import foro.hub.apiRest.domain.DTO.TopicoDTO;
import foro.hub.apiRest.domain.DTO.TopicoDetallesDTO;
import foro.hub.apiRest.domain.response.ResponseBuilder;
import foro.hub.apiRest.domain.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService service;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TopicoDetallesDTO>> obtenerUnTopico(@PathVariable Long id) {

        var topico = service.obtenerUnTopico(id);

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        "El tópico solicitado cargó correctamente",
                        topico
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TopicoDetallesDTO>>> obtenerTopicos() {

        var lista = service.obtenerTodosTopicos();

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        "Lista de tópicos obtenida correctamente",
                        lista
                )
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TopicoDetallesDTO>> nuevoTopico(@RequestBody @Valid TopicoDTO data,
            UriComponentsBuilder uriBuilder) {

        var topico = service.crearTopico(data);

        var uri = uriBuilder
                .path("/topicos/{id}")
                .buildAndExpand(topico.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(ResponseBuilder.success(
                        "Tópico creado correctamente",
                        new TopicoDetallesDTO(topico)
                ));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<TopicoDetallesDTO>> actualizarTopico(@RequestBody @Valid PutTopico data) {
        var topico = service.actualizarTopico(data);

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        "Se actualizó el tópico correctamente.",
                        new TopicoDetallesDTO(topico)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TopicoDetallesDTO>> activarTopico(@PathVariable Long id) {
        var topico = service.cambiarActivo(id, true);

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        "Se activó el tópico.",
                        new TopicoDetallesDTO(topico)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<TopicoDetallesDTO>> inactivarTopico(@PathVariable Long id) {
        var topico = service.cambiarActivo(id, false);

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        "Se inactivó el tópico.",
                        new TopicoDetallesDTO(topico)
                )
        );
    }
}
