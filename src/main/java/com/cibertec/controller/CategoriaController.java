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

import com.cibertec.model.Categoria;
import com.cibertec.service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {
	@Autowired
    private CategoriaService service;

    @GetMapping
    public List<Categoria> listar() {
        return service.listarTodas();
    }

    @GetMapping("/activas")
    public List<Categoria> listarActivas() {
        return service.listarActivas();
    }

    @PostMapping
    public Categoria crear(@RequestBody Categoria cat) {
        return service.guardar(cat);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Categoria> editar(@PathVariable Integer id, @RequestBody Categoria datos) {
        Categoria cat = service.buscarPorId(id);

        if (cat != null) {
            if (datos.getNombre() != null) cat.setNombre(datos.getNombre());
            if (datos.getDescripcion() != null) cat.setDescripcion(datos.getDescripcion());
            if (datos.getActivo() != null) cat.setActivo(datos.getActivo());
            if (datos.getIcono() != null) cat.setIcono(datos.getIcono());   // AGREGAR
            if (datos.getColor() != null) cat.setColor(datos.getColor());   // AGREGAR

            return ResponseEntity.ok(service.guardar(cat));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
