package com.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.model.MensajeChat;
import com.cibertec.repository.MensajeChatRepository;

@Service
public class MensajeChatService {
	@Autowired
    private MensajeChatRepository repository;

    public MensajeChat guardar(MensajeChat mensaje) {
        return repository.save(mensaje);
    }

    public List<MensajeChat> listarPorSesion(Integer sesionId) {
        return repository.findBySesionIdOrderByCreadoEnAsc(sesionId);
    }
}
