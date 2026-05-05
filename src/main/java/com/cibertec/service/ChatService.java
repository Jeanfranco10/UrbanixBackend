package com.cibertec.service;

import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.cibertec.model.MensajeChat;
import com.cibertec.model.SesionChat;
import com.cibertec.repository.MensajeChatRepository;
import com.cibertec.repository.SesionChatRepository;
import com.cibertec.enums.Modelos_enum.EstadoChat;

@Service
public class ChatService {
	@Autowired
    private SesionChatRepository sesionRepo;

    @Autowired
    private MensajeChatRepository mensajeRepo;

    public SesionChat obtenerSesionActiva(Integer usuarioId) {
        return sesionRepo.findByUsuarioIdAndEstado(usuarioId, EstadoChat.activo).orElse(null);
    }

    public SesionChat crearSesion(SesionChat sesion) {
        return sesionRepo.save(sesion);
    }

    public MensajeChat enviarMensaje(MensajeChat mensaje) {
        return mensajeRepo.save(mensaje);
    }

    public List<MensajeChat> obtenerHistorial(Integer sesionId) {
        return mensajeRepo.findBySesionIdOrderByCreadoEnAsc(sesionId);
    }
}
