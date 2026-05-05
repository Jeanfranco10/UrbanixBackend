package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cibertec.model.TokenDispositivo;
import com.cibertec.service.TokenDispositivoService;

@RestController
@RequestMapping("/api/tokens")
@CrossOrigin(origins = "*")
public class TokenDispositivoController {

    @Autowired
    private TokenDispositivoService service;

    @PostMapping
    public TokenDispositivo registrar(@RequestBody TokenDispositivo token) {
        return service.registrarToken(token);
    }

    @DeleteMapping("/usuario/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminarTokensUsuario(id);
    }
}