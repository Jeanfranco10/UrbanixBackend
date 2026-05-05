package com.cibertec.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cibertec.model.EvidenciaResolucion;
import com.cibertec.service.EvidenciaResolucionService;

import java.util.List;

@RestController
@RequestMapping("/api/evidencias-resolucion")
@CrossOrigin(origins = "*")
public class EvidenciaResolucionController {

    @Autowired
    private EvidenciaResolucionService service;

    @PostMapping
    public EvidenciaResolucion guardar(@RequestBody EvidenciaResolucion evidencia) {
        return service.guardar(evidencia);
    }

    @GetMapping("/caso/{id}")
    public List<EvidenciaResolucion> listarPorCaso(@PathVariable Integer id) {
        return service.listarPorCaso(id);
    }
}
