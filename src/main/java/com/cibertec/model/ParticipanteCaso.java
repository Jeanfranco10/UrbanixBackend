package com.cibertec.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "participantes_caso")
@Getter @Setter
public class ParticipanteCaso {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "caso_id", nullable = false)
    private Caso caso;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "unido_en", updatable = false)
    private LocalDateTime unidoEn = LocalDateTime.now();
}
