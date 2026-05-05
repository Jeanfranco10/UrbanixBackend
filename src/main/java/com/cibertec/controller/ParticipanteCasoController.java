package com.cibertec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.model.ParticipanteCaso;
import com.cibertec.service.PaticipanteCasoServoce;

@RestController
@RequestMapping("/api/participantes-casos")
@CrossOrigin(origins = "*")
public class ParticipanteCasoController {
    @Autowired
    private PaticipanteCasoServoce service; // Nombre exacto de tu Service

    @PostMapping("/unirse")
    public ParticipanteCaso unirse(@RequestBody ParticipanteCaso participante) {
        return service.unirseACaso(participante);
    }

    @GetMapping("/caso/{id}")
    public List<ParticipanteCaso> listar(@PathVariable Integer id) {
        return service.listarPorCaso(id);
    }
}