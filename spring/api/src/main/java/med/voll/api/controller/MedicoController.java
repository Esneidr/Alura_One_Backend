package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.DTO.GetMedicosDTO;
import med.voll.api.domain.DTO.MedicoDTO;
import med.voll.api.domain.DTO.MedicoDetailsDTO;
import med.voll.api.domain.DTO.PutMedicoDTO;
import med.voll.api.domain.model.Medico;
import med.voll.api.domain.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity MedicoDetalle(@PathVariable Long id) {
        var medio = repository.getReferenceById(id);

        return ResponseEntity.ok(new MedicoDetailsDTO(medio));
    }

    @GetMapping
    public ResponseEntity<Page<GetMedicosDTO>> getAllDoctors(@PageableDefault(size = 10, sort={"nombre"}) Pageable paginacion) {
        var page = repository.findAllByActivoTrue(paginacion).map(GetMedicosDTO::new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @PostMapping
    public ResponseEntity createDoctor(@RequestBody @Valid MedicoDTO data, UriComponentsBuilder uriComponentsBuilder) {
        var medico = new Medico(data);
        repository.save(medico);

        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new MedicoDetailsDTO(medico));
    }

    @Transactional
    @PutMapping
    public ResponseEntity updateMedico(@RequestBody @Valid PutMedicoDTO data) {
        var medico = repository.getReferenceById(data.id());
        medico.changeMedico(data);

        return  ResponseEntity.ok(new MedicoDetailsDTO(medico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deleteMedico(@PathVariable Long id) {
        var medio = repository.getReferenceById(id);
        medio.inactivar();

        return ResponseEntity.noContent().build();
    }
}
