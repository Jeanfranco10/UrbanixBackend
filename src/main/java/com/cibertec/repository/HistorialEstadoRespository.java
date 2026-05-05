package com.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.model.HistorialEstado;

@Repository
public interface HistorialEstadoRespository extends JpaRepository<HistorialEstado, Integer>{
	List<HistorialEstado> findByIncidenciaIdOrderByCreadoEnDesc(Integer incidenciaId);
}
