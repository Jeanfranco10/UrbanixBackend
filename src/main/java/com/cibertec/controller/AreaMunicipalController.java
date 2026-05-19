package com.cibertec.controller;

import java.util.List;

import com.cibertec.dto.AreaResumenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cibertec.model.AreaMunicipal;
import com.cibertec.service.AreaMunicipalService;

@RestController
@RequestMapping("/api/areas")
@CrossOrigin(origins = "*")
public class AreaMunicipalController {
	@Autowired
    private AreaMunicipalService service;

    @GetMapping
    public List<AreaResumenDTO> listar() {
        return service.listarTodasConResumen();
    }

    @PostMapping
    public AreaMunicipal crear(@RequestBody AreaMunicipal area) {
        return service.guardar(area);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AreaMunicipal> editar(@PathVariable Integer id, @RequestBody AreaMunicipal datos) {
        AreaMunicipal area = service.buscarPorId(id);
        if (area != null) {
            if (datos.getNombre() != null) area.setNombre(datos.getNombre());
            if (datos.getDescripcion() != null) area.setDescripcion(datos.getDescripcion());
            if (datos.getActivo() != null) area.setActivo(datos.getActivo());
            if (datos.getResponsable() != null) area.setResponsable(datos.getResponsable()); // AGREGAR
            if (datos.getDistritos() != null) area.setDistritos(datos.getDistritos()); // AGREGAR
            return ResponseEntity.ok(service.guardar(area));
        }
        return ResponseEntity.notFound().build();
    }
}
