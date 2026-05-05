package com.cibertec.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "configuracion_usuario")
@Getter @Setter
public class ConfiguracionUsuario {
 
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;

    @Column(name = "notif_cambio_estado")
    private Boolean notifCambioEstado = true;

    @Column(name = "notif_caso_acumulativo")
    private Boolean notifCasoAcumulativo = true;

    @Column(name = "notif_resolucion")
    private Boolean notifResolucion = true;

    @Column(name = "notif_asignacion")
    private Boolean notifAsignacion = true;

    @Column(name = "ubicacion_automatica")
    private Boolean ubicacionAutomatica = true;

    @Column(length = 10)
    private String idioma = "es";

    @Column(length = 10)
    private String tema = "oscuro";

    @Column(name = "perfil_publico")
    private Boolean perfilPublico = false;

    @Column(name = "actualizado_en")
    private LocalDateTime actualizadoEn = LocalDateTime.now();
}
