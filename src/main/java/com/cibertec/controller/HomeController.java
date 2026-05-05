package com.cibertec.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> home() {
        return ResponseEntity.ok(Map.of(
            "proyecto", "UrbanixApi",
            "descripcion", "API REST para el sistema de gestión de incidencias urbanas",
            "version", "1.0.0",
            "estado", "activo",
            "documentacion", "/api",
            "endpoints", Map.of(
                "incidencias", "/api/incidencias",
                "casos", "/api/casos",
                "areas", "/api/areas",
                "categorias", "/api/categorias",
                "usuarios", "/api/usuarios",
                "ubicaciones", "/api/ubicaciones"
            ),
            "timestamp", LocalDateTime.now().toString()
        ));
    }
}
