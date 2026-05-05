package com.cibertec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cibertec.model.Ubicacion;
import com.cibertec.service.UbicacionServices;


@RestController
@RequestMapping("/api/ubicaciones")
@CrossOrigin(origins = "*")
public class UbicacionController {
	@Autowired
    private UbicacionServices service;

    @GetMapping
    public List<Ubicacion> listar() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ubicacion> obtener(@PathVariable Integer id) {
        Ubicacion u = service.buscarPorId(id);
        return (u != null) ? ResponseEntity.ok(u) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Ubicacion crear(@RequestBody Ubicacion ubicacion) {
        return service.guardar(ubicacion);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ubicacion> editar(@PathVariable Integer id, @RequestBody Ubicacion datos) {
        Ubicacion u = service.buscarPorId(id);
        if (u != null) {
            if (datos.getDireccion() != null) u.setDireccion(datos.getDireccion());
            if (datos.getLatitud() != null) u.setLatitud(datos.getLatitud());
            if (datos.getLongitud() != null) u.setLongitud(datos.getLongitud());
            if (datos.getDistrito() != null) u.setDistrito(datos.getDistrito());
            if (datos.getReferencia() != null) u.setReferencia(datos.getReferencia()); // AGREGAR
            if (datos.getCiudad() != null) u.setCiudad(datos.getCiudad());             // AGREGAR
            if (datos.getPais() != null) u.setPais(datos.getPais());
            return ResponseEntity.ok(service.guardar(u));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
