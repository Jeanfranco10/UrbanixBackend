package com.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.model.Ubicacion;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion, Integer> {
	List<Ubicacion> findByDistrito(String distrito);
    
    List<Ubicacion> findByDireccionContainingIgnoreCase(String direccion);
}
