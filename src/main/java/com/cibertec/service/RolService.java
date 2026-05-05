package com.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.model.Roles;
import com.cibertec.repository.RolRepository;

@Service
public class RolService {
	@Autowired
    private RolRepository repository;

    public List<Roles> listarTodos() {
        return repository.findAll();
    }

    public Roles buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
