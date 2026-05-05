package com.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.model.ArchivoMultimedia;
import com.cibertec.repository.ArchivoMultimediaRepository;

@Service
public class ArchivoMultimediaService {
	@Autowired
    private ArchivoMultimediaRepository repository;

    public ArchivoMultimedia guardar(ArchivoMultimedia archivo) {
        return repository.save(archivo);
    }

    public List<ArchivoMultimedia> listarPorIncidencia(Integer incidenciaId) {
        return repository.findByIncidenciaId(incidenciaId);
    }

    public ArchivoMultimedia buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
