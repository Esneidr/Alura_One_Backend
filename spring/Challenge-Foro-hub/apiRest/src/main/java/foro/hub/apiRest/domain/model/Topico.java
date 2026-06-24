package foro.hub.apiRest.domain.model;

import foro.hub.apiRest.domain.DTO.PutTopico;
import foro.hub.apiRest.domain.DTO.TopicoDTO;
import foro.hub.apiRest.domain.ENUM.Estado;
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
    private String titulo;
    private String mensaje;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    private Boolean activo;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;


    public Topico(TopicoDTO data) {
        this.id = null;
        this.autor = data.autor();
        this.titulo = data.titulo();
        this.mensaje = data.mensaje();
        this.curso = data.curso();
        this.estado = Estado.ABIERTO;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
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

        if (data.fechaActualizacion() != null) {
            this.fechaActualizacion = data.fechaActualizacion();
        }

        if (data.estado() != null) {
            this.estado = data.estado();
        }

        if (data.activo() != null) {
            this.activo = data.activo();
        }
    }

    public void inactivar() {
        cambiarEstado(false, "El tópico ya está inactivo.");
    }

    public void activar() {
        cambiarEstado(true, "El tópico ya está activo.");
    }

    private void cambiarEstado(boolean nuevoEstado, String mensajeError) {
        if (this.activo == nuevoEstado) {
            throw new ValidacionException(mensajeError);
        }
        this.activo = nuevoEstado;
    }

}
