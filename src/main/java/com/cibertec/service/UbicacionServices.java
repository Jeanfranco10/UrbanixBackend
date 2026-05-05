package com.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.model.Ubicacion;
import com.cibertec.repository.UbicacionRepository;

@Service
public class UbicacionServices {
	@Autowired
    private UbicacionRepository repository;

    public Ubicacion guardar(Ubicacion ubicacion) {
        return repository.save(ubicacion);
    }

    public List<Ubicacion> listarTodas() {
        return repository.findAll();
    }

    public Ubicacion buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
