package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.DTO.GetPacientesDTO;
import med.voll.api.domain.DTO.PacienteDTO;
import med.voll.api.domain.DTO.PacienteDetailsDTO;
import med.voll.api.domain.model.Paciente;
import med.voll.api.domain.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @GetMapping
    public ResponseEntity<Page<GetPacientesDTO>> getAllPacientes(@PageableDefault(size = 10, sort={"nombre"}) Pageable paginacion) {
        var page = repository.findAllByActivoTrue(paginacion).map(GetPacientesDTO::new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @PostMapping
    public ResponseEntity createPaciente(@RequestBody @Valid PacienteDTO data, UriComponentsBuilder uriComponentsBuilder) {
        var paciente = new Paciente(data);
        repository.save(paciente);

        var uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return  ResponseEntity.created(uri).body(new PacienteDetailsDTO(paciente));
    }
}
