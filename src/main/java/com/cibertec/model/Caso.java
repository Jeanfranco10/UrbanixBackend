package com.cibertec.model;


import com.cibertec.enums.Modelos_enum.EstadoIncidencia;
import com.cibertec.enums.Modelos_enum.NivelPrioridad;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "casos")
@Getter @Setter
public class Caso {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "ubicacion_id")
    private Ubicacion ubicacion;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private AreaMunicipal area;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(columnDefinition = "estado_incidencia")
    private EstadoIncidencia estado = EstadoIncidencia.pendiente;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(columnDefinition = "nivel_prioridad")
    private NivelPrioridad prioridad = NivelPrioridad.media;

    @Column(name = "total_reportes")
    private Integer totalReportes = 1;

    @Column(name = "radio_metros")
    private Integer radioMetros = 200;

    @OneToMany(mappedBy = "caso")
    @JsonIgnore
    private List<Incidencia> incidencias;

    @Column(name = "creado_en")
    private LocalDateTime creadoEn = LocalDateTime.now();
}
