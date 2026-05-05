package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cibertec.model.ConfiguracionUsuario;
import com.cibertec.service.ConfiguracionUsuarioService;

@RestController
@RequestMapping("/api/configuraciones")
@CrossOrigin(origins = "*")
public class ConfiguracionUsuarioController {

    @Autowired
    private ConfiguracionUsuarioService service;

    @GetMapping("/usuario/{id}")
    public ResponseEntity<ConfiguracionUsuario> obtener(@PathVariable Integer id) {
        ConfiguracionUsuario config = service.buscarPorUsuario(id);
        return (config != null) ? ResponseEntity.ok(config) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ConfiguracionUsuario> actualizar(@PathVariable Integer id, @RequestBody ConfiguracionUsuario datos) {
        ConfiguracionUsuario configExistente = service.buscarPorUsuario(id);
        if (configExistente != null) {
            if (datos.getTema() != null) configExistente.setTema(datos.getTema());
            if (datos.getIdioma() != null) configExistente.setIdioma(datos.getIdioma());
            return ResponseEntity.ok(service.actualizar(configExistente));
        }
        return ResponseEntity.notFound().build();
    }
}
