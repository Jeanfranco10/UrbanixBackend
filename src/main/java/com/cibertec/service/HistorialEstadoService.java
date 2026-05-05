package com.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.model.HistorialEstado;
import com.cibertec.repository.HistorialEstadoRespository;

@Service
public class HistorialEstadoService {
	@Autowired
    private HistorialEstadoRespository repository;

    public HistorialEstado registrarCambio(HistorialEstado historial) {
        return repository.save(historial);
    }

    public List<HistorialEstado> obtenerHistorial(Integer incidenciaId) {
        return repository.findByIncidenciaIdOrderByCreadoEnDesc(incidenciaId);
    }
}
