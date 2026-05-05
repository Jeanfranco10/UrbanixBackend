package com.cibertec.model;

import java.time.LocalDateTime;

import com.cibertec.enums.Modelos_enum.TipoArchivo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "evidencia_resolucion")
@Getter @Setter
public class EvidenciaResolucion {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "caso_id", nullable = false)
    private Caso caso;

    @ManyToOne
    @JoinColumn(name = "inspector_id", nullable = false)
    private Usuario inspector;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(columnDefinition = "tipo_archivo")
    private TipoArchivo tipo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String url;

    @Column(name = "nombre_archivo")
    private String nombreArchivo;

    @Column(name = "tamano_bytes")
    private Integer tamanoBytes;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "creado_en", updatable = false)
    private LocalDateTime creadoEn = LocalDateTime.now();
}
