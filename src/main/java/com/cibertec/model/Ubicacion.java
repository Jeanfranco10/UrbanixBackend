package com.cibertec.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ubicaciones")
@Getter @Setter
public class Ubicacion {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(precision = 10, scale = 7, nullable = false)
    private BigDecimal latitud;

    @Column(precision = 10, scale = 7, nullable = false)
    private BigDecimal longitud;

    private String direccion;
    private String referencia;
    private String distrito;
    
    @Column(nullable = false)
    private String ciudad = "Lima";

    @Column(nullable = false)
    private String pais = "Perú";
}
