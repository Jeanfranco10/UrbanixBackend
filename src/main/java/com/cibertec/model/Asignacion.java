package com.cibertec.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "asignaciones")
@Getter @Setter
public class Asignacion {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "caso_id", nullable = false)
    private Caso caso;

    @ManyToOne
    @JoinColumn(name = "asignado_a", nullable = false)
    private Usuario asignadoA;

    @ManyToOne
    @JoinColumn(name = "asignado_por", nullable = false)
    private Usuario asignadoPor;

    @Column(name = "fecha_asignacion")
    private LocalDateTime fechaAsignacion = LocalDateTime.now();

    @Column(name = "fecha_limite")
    private LocalDateTime fechaLimite;

    @Column(name = "fecha_resolucion")
    private LocalDateTime fechaResolucion;

    @Column(columnDefinition = "TEXT")
    private String notas;

    private Boolean activo = true;
}
