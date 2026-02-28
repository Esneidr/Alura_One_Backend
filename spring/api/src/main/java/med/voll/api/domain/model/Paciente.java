package med.voll.api.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.DTO.PacienteDTO;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "pacientes")
@Entity(name = "Paciente" )
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String documento;
    private String telefono;
    private Boolean activo;

    @Embedded
    private Direccion direccion;

    public Paciente(PacienteDTO data) {
        this.id = null;
        this.nombre = data.nombre();
        this.email = data.email();
        this.documento = data.documento();
        this.telefono = data.telefono();
        this.activo = true;
        this.direccion = new Direccion(data.direccion());
    }

}
