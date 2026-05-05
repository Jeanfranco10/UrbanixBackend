package com.cibertec.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cibertec.model.MensajeChat;
import com.cibertec.service.MensajeChatService;

@RestController
@RequestMapping("/api/mensajes")
@CrossOrigin(origins = "*")
public class MensajeController {

    @Autowired
    private MensajeChatService service;

    @PostMapping
    public MensajeChat enviar(@RequestBody MensajeChat mensaje) {
        return service.guardar(mensaje);
    }

    @GetMapping("/sesion/{id}")
    public List<MensajeChat> historialPorSesion(@PathVariable Integer id) {
        return service.listarPorSesion(id);
    }
}