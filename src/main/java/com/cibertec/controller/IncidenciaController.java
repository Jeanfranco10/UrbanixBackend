package com.cibertec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.model.Incidencia;
import com.cibertec.service.IncidenciasServices;

@RestController
@RequestMapping("/api/incidencias")
@CrossOrigin(origins = "*")
public class IncidenciaController {
	
	@Autowired
    private IncidenciasServices service;

    @GetMapping
    public List<Incidencia> listar() {
        return service.listarTodas();
    }

    @PostMapping
    public Incidencia crear(@RequestBody Incidencia incidencia) {
        return service.registrar(incidencia);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Incidencia> editar(@PathVariable Integer id, @RequestBody Incidencia datos) {
        Incidencia inc = service.buscarPorId(id).orElse(null);
        
        if (inc != null) {
            if (datos.getDescripcion() != null) inc.setDescripcion(datos.getDescripcion());
            if (datos.getEstado() != null) inc.setEstado(datos.getEstado());
            if (datos.getPrioridad() != null) inc.setPrioridad(datos.getPrioridad());
            return ResponseEntity.ok(service.registrar(inc));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Incidencia> porUsuario(@PathVariable Integer usuarioId) {
        return service.listarPorUsuario(usuarioId);
    }

    // 1. BUSCAR POR ID — Lo usa DetalleIncidencia.jsx
    @GetMapping("/{id}")
    public ResponseEntity<Incidencia> buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // 2. LISTAR POR CASO — Lo usa Casos.jsx (expandible)
    @GetMapping("/caso/{casoId}")
    public List<Incidencia> porCaso(@PathVariable Integer casoId) {
        return service.listarPorCaso(casoId);
    }
}
