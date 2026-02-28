package med.voll.api.domain.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.DTO.MedicoDTO;
import med.voll.api.domain.DTO.PutMedicoDTO;
import med.voll.api.domain.Enum.Especialidad;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "medicos")
@Entity(name = "Medico" )
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String documento;
    private String telefono;
    private Boolean activo;

    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;

    @Embedded
    private Direccion direccion;

    public Medico(MedicoDTO data) {
        this.id = null;
        this.nombre = data.nombre();
        this.email = data.email();
        this.documento = data.documento();
        this.telefono = data.telefono();
        this.activo = true;
        this.especialidad = data.especialidad();
        this.direccion = new Direccion(data.direccion());
    }

    public void changeMedico(@Valid PutMedicoDTO data) {
        if (data.nombre() != null) {
            this.nombre = data.nombre();
        }

        if (data.telefono() != null) {
            this.telefono = data.telefono();
        }

        if (data.direccion() != null) {
            this.direccion.PutDireccion(data.direccion());
        }
    }

    public void inactivar() {
        this.activo = false;
    }
}
