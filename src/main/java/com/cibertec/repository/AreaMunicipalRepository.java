package com.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.model.AreaMunicipal;

@Repository
public interface AreaMunicipalRepository extends JpaRepository<AreaMunicipal, Integer> {
	List<AreaMunicipal> findByActivoTrue();
    
    // Buscar por el responsable asignado
    List<AreaMunicipal> findByResponsableId(Integer responsableId);
}
