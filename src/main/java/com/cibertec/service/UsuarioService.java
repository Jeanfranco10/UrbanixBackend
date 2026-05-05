package com.cibertec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.model.Usuario;
import com.cibertec.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
    private UsuarioRepository repository;

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public Usuario guardar(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario buscarPorCorreo(String correo) {
        return repository.findByCorreo(correo).orElse(null);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
    
    public Usuario buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
