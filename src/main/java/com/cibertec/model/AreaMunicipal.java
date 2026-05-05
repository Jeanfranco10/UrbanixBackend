package com.cibertec.model;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "areas_municipales")
@Getter @Setter
public class AreaMunicipal {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 120)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;


    @Column(name = "distritos")
    private String[] distritos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsable_id")
    @JsonIgnoreProperties({"area", "actualizadoEn", "creadoEn", "contrasenaHash"})
    private Usuario responsable;

    private Boolean activo = true;

    @Column(name = "creado_en", updatable = false)
    private LocalDateTime creadoEn = LocalDateTime.now();
}
