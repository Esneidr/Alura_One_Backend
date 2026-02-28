package foro.hub.apiRest.domain.model;

import foro.hub.apiRest.domain.DTO.PutTopico;
import foro.hub.apiRest.domain.DTO.TopicoDTO;
import foro.hub.apiRest.domain.ENUM.Curso;
import foro.hub.apiRest.infra.GestorErrores.ValidacionException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "topicos")
@Entity(name = "Topico" )
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String autor;
    private String titulo;
    private String mensaje;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    private Boolean activo;

    @Enumerated(EnumType.STRING)
    private Curso curso;

    public Topico(TopicoDTO data) {
        this.id = null;
        this.autor = data.autor();
        this.titulo = data.titulo();
        this.mensaje = data.mensaje();
        this.curso = data.curso();
        this.fechaCreacion = LocalDateTime.now();
        this.activo = true;
    }

    public void actualizarTopico(PutTopico data) {
        if (data.titulo() != null) {
            this.titulo = data.titulo();
        }

        if (data.mensaje() != null) {
            this.mensaje = data.mensaje();
        }

        if (data.autor() != null) {
            this.autor = data.autor();
        }

        if (data.curso() != null) {
            this.curso = data.curso();
        }
    }

    public void inactivar() {
        if (!this.activo) {
            throw new ValidacionException("El tópico ya está inactivo.");
        }
        this.activo = false;
    }
}
