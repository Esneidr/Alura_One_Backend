package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.DTO.CancelarDTO;
import med.voll.api.domain.DTO.ConsultaDTO;
import med.voll.api.domain.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private ConsultaService service;

    @PostMapping
    @Transactional
    public ResponseEntity reservar(@RequestBody @Valid ConsultaDTO data) {

        var consulta = service.reservar(data);

        return  ResponseEntity.ok(consulta);
    }

    @DeleteMapping
    @Transactional
    public  ResponseEntity cancelar(@RequestBody @Valid CancelarDTO data) {
        service.cancelar(data);
        return ResponseEntity.noContent().build();
    }
}
