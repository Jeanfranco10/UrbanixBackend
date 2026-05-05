package com.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.model.Caso;
import com.cibertec.repository.CasoRepository;

@Service
public class CasoService {
	@Autowired
    private CasoRepository repository;

    public List<Caso> listarTodos() {
        return repository.findAll();
    }

    public Caso guardar(Caso caso) {
        return repository.save(caso);
    }
    public Caso buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
