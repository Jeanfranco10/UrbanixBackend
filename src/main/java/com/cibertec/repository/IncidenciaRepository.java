package com.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.enums.Modelos_enum.EstadoIncidencia;
import com.cibertec.model.Incidencia;


@Repository
public interface IncidenciaRepository extends JpaRepository<Incidencia, Integer> {
	List<Incidencia> findByUsuarioIdOrderByCreadoEnDesc(Integer usuarioId);
    
    List<Incidencia> findByEstado(EstadoIncidencia estado);
    
    // Buscar por código único de ticket
    Incidencia findByCodigo(String codigo);

    List<Incidencia>findByCasoId(Integer casoId);
	
}
