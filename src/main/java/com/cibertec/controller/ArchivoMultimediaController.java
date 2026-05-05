package com.cibertec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.model.ArchivoMultimedia;
import com.cibertec.service.ArchivoMultimediaService;

@RestController
@RequestMapping("/api/archivos")
@CrossOrigin(origins = "*")
public class ArchivoMultimediaController {
	@Autowired
    private ArchivoMultimediaService service;

    @PostMapping
    public ArchivoMultimedia subir(@RequestBody ArchivoMultimedia archivo) {
        return service.guardar(archivo);
    }

    @GetMapping("/incidencia/{id}")
    public List<ArchivoMultimedia> listarPorIncidencia(@PathVariable Integer id) {
        return service.listarPorIncidencia(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
