package com.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.model.AreaMunicipal;
import com.cibertec.repository.AreaMunicipalRepository;

@Service
public class AreaMunicipalService {
	@Autowired
    private AreaMunicipalRepository repository;

    public List<AreaMunicipal> listarTodas() {
        return repository.findAll();
    }

    public List<AreaMunicipal> listarActivas() {
        return repository.findByActivoTrue();
    }

    public AreaMunicipal guardar(AreaMunicipal area) {
        return repository.save(area);
    }

    public AreaMunicipal buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
