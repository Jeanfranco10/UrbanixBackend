package com.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.model.Notificacion;
import com.cibertec.repository.NotificacionRepository;

@Service
public class NotificacionService {
	@Autowired
    private NotificacionRepository repository;

    public List<Notificacion> listarPorUsuario(Integer usuarioId) {
        return repository.findByUsuarioIdOrderByCreadoEnDesc(usuarioId);
    }

    public void marcarComoLeida(Integer id) {
        repository.findById(id).ifPresent(n -> {
            n.setLeida(true);
            repository.save(n);
        });
    }

    public long contarNoLeidas(Integer usuarioId) {
        return repository.countByUsuarioIdAndLeidaFalse(usuarioId);
    }
}
