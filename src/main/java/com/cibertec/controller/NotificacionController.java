package com.cibertec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.model.Notificacion;
import com.cibertec.service.NotificacionService;

@RestController
@RequestMapping("/api/notificaciones")
@CrossOrigin(origins = "*")
public class NotificacionController {
	@Autowired
    private NotificacionService service;

    @GetMapping("/usuario/{id}")
    public List<Notificacion> listar(@PathVariable Integer id) {
        return service.listarPorUsuario(id);
    }

    @PatchMapping("/{id}/leer")
    public void leer(@PathVariable Integer id) {
        service.marcarComoLeida(id);
    }

    @GetMapping("/usuario/{id}/conteo")
    public long contarNoLeidas(@PathVariable Integer id) {
        return service.contarNoLeidas(id);
    }

}
