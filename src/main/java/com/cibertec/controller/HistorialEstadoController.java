package com.cibertec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.model.HistorialEstado;
import com.cibertec.service.HistorialEstadoService;

@RestController
@RequestMapping("/api/historial")
@CrossOrigin(origins = "*")
public class HistorialEstadoController {
	@Autowired
    private HistorialEstadoService service;

    @PostMapping
    public HistorialEstado registrar(@RequestBody HistorialEstado historial) {
        return service.registrarCambio(historial);
    }

    @GetMapping("/incidencia/{id}")
    public List<HistorialEstado> verHistorial(@PathVariable Integer id) {
        return service.obtenerHistorial(id);
    }
}
