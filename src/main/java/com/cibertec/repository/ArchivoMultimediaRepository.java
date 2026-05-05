package com.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.model.ArchivoMultimedia;

@Repository
public interface ArchivoMultimediaRepository extends JpaRepository<ArchivoMultimedia, Integer> {
	List<ArchivoMultimedia> findByIncidenciaId(Integer incidenciaId);
}
