package com.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.model.Categoria;
import com.cibertec.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
    private CategoriaRepository repository;

    public List<Categoria> listarActivas() {
        return repository.findByActivoTrue();
    }

    public List<Categoria> listarTodas() {
        return repository.findAll();
    }

    public Categoria buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }
    
    public Categoria guardar(Categoria cat) {
        return repository.save(cat);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
