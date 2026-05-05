package com.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.model.Asignacion;
import com.cibertec.repository.AsignacionRepository;

@Service
public class AsignacionService {
	@Autowired
    private AsignacionRepository repository;

    public List<Asignacion> listarPorInspector(Integer inspectorId) {
        return repository.findByAsignadoAIdAndActivoTrue(inspectorId);
    }
    
    public Asignacion crear(Asignacion asignacion) {
        return repository.save(asignacion);
    }
    public List<Asignacion> listarPorCaso(Integer casoId) {
        return repository.findByCasoIdOrderByFechaAsignacionDesc(casoId);
    }
}
