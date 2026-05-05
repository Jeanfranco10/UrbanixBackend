package com.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.enums.Modelos_enum.EstadoIncidencia;
import com.cibertec.enums.Modelos_enum.NivelPrioridad;
import com.cibertec.model.Caso;

@Repository
public interface CasoRepository extends JpaRepository<Caso, Integer>{
	List<Caso> findByEstado(EstadoIncidencia estado);
    
    List<Caso> findByPrioridad(NivelPrioridad prioridad);
    
    List<Caso> findByAreaId(Integer areaId);
}
