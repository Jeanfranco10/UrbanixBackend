package com.cibertec.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cibertec.model.MensajeChat;
import com.cibertec.model.SesionChat;
import com.cibertec.service.ChatService;
import com.cibertec.service.MensajeChatService;


@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {
	@Autowired
    private ChatService chatService;
    
    @Autowired
    private MensajeChatService mensajeService;

    @GetMapping("/sesion/activa/{usuarioId}")
    public SesionChat obtenerSesion(@PathVariable Integer usuarioId) {
        return chatService.obtenerSesionActiva(usuarioId);
    }

    @PostMapping("/sesion")
    public SesionChat iniciarSesion(@RequestBody SesionChat sesion) {
        return chatService.crearSesion(sesion);
    }

    @PostMapping("/mensaje")
    public MensajeChat enviar(@RequestBody MensajeChat mensaje) {
        return mensajeService.guardar(mensaje);
    }

    @GetMapping("/historial/{sesionId}")
    public List<MensajeChat> verHistorial(@PathVariable Integer sesionId) {
        return mensajeService.listarPorSesion(sesionId);
    }
}
