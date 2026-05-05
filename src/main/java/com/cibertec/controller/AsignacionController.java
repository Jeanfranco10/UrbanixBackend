package com.cibertec.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cibertec.model.Asignacion;
import com.cibertec.service.AsignacionService;

@RestController
@RequestMapping("/api/asignaciones")
@CrossOrigin(origins = "*")
public class AsignacionController {

    @Autowired
    private AsignacionService service;

    @PostMapping
    public Asignacion asignar(@RequestBody Asignacion asignacion) {
        return service.crear(asignacion);
    }

    @GetMapping("/inspector/{id}")
    public List<Asignacion> listarPorInspector(@PathVariable Integer id) {
        return service.listarPorInspector(id);
    }

    @GetMapping("/caso/{id}")
    public List<Asignacion> listarPorCaso(@PathVariable Integer id) {
        return service.listarPorCaso(id);
    }
}

