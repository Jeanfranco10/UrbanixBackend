package com.cibertec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.model.TokenDispositivo;
import com.cibertec.repository.TokenDispositivoRepository;

@Service
public class TokenDispositivoService {
	@Autowired
    private TokenDispositivoRepository repository;

    public TokenDispositivo registrarToken(TokenDispositivo token) {
        return repository.save(token);
    }

    public void eliminarTokensUsuario(Integer usuarioId) {
        repository.deleteByUsuarioId(usuarioId);
    }
}
