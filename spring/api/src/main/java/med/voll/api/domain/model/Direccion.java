package med.voll.api.domain.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.DTO.DireccionDTO;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Direccion {
    private String calle;
    private String numero;
    private String complemento;
    private String barrio;
    private String codigo_postal;
    private String ciudad;
    private String estado;

    public Direccion(DireccionDTO direccionDTO) {
        this.calle = direccionDTO.calle();
        this.numero = direccionDTO.numero();
        this.complemento = direccionDTO.complemento();
        this.barrio = direccionDTO.barrio();
        this.codigo_postal = direccionDTO.codigo_postal();
        this.ciudad = direccionDTO.ciudad();
        this.estado = direccionDTO.estado();
    }

    public void PutDireccion(DireccionDTO data) {
        if (data.calle() != null) {
            this.calle = data.calle();
        }

        if (data.numero() != null) {
            this.numero = data.numero();
        }

        if (data.complemento() != null) {
            this.complemento = data.complemento();
        }

        if (data.barrio() != null) {
            this.barrio = data.barrio();
        }

        if (data.codigo_postal() != null) {
            this.codigo_postal = data.codigo_postal();
        }

        if (data.ciudad() != null) {
            this.ciudad = data.ciudad();
        }

        if (data.estado() != null) {
            this.estado = data.estado();
        }
    }
}
