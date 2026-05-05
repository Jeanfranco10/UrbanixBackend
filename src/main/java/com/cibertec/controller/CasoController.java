package com.cibertec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cibertec.model.Caso;
import com.cibertec.service.CasoService;

@RestController
@RequestMapping("/api/casos")
@CrossOrigin(origins = "*")
public class CasoController {
    @Autowired
    private CasoService service;

    @GetMapping
    public List<Caso> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public Caso crear(@RequestBody Caso caso) {
        return service.guardar(caso);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Caso> editar(@PathVariable Integer id, @RequestBody Caso datos) {
        Caso caso = service.buscarPorId(id);
        if (caso != null) {
            if (datos.getTitulo() != null) caso.setTitulo(datos.getTitulo());
            if (datos.getDescripcion() != null) caso.setDescripcion(datos.getDescripcion());
            if (datos.getEstado() != null) caso.setEstado(datos.getEstado());
            if (datos.getPrioridad() != null) caso.setPrioridad(datos.getPrioridad());
            if (datos.getArea() != null) caso.setArea(datos.getArea());
            if (datos.getCategoria() != null) caso.setCategoria(datos.getCategoria());
            if (datos.getUbicacion() != null) caso.setUbicacion(datos.getUbicacion());
            if (datos.getRadioMetros() != null) caso.setRadioMetros(datos.getRadioMetros());
            return ResponseEntity.ok(service.guardar(caso));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}