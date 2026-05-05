package com.cibertec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.model.ConfiguracionUsuario;
import com.cibertec.repository.ConfiguracionUsuarioRepository;

@Service
public class ConfiguracionUsuarioService {
	@Autowired
    private ConfiguracionUsuarioRepository repository;

    public ConfiguracionUsuario buscarPorUsuario(Integer usuarioId) {
        return repository.findByUsuarioId(usuarioId).orElse(null);
    }

    public ConfiguracionUsuario actualizar(ConfiguracionUsuario config) {
        return repository.save(config);
    }
}
