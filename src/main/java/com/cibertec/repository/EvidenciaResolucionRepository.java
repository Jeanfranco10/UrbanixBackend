package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.model.EvidenciaResolucion;

import java.util.List;

@Repository
public interface EvidenciaResolucionRepository extends JpaRepository<EvidenciaResolucion, Integer>{

    List<EvidenciaResolucion> findByCasoId(Integer casoId);

}
