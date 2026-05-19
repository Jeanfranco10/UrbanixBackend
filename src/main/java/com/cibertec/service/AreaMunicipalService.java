package com.cibertec.service;

import java.util.List;

import com.cibertec.dto.AreaResumenDTO;
import com.cibertec.model.Caso;
import com.cibertec.repository.CasoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.model.AreaMunicipal;
import com.cibertec.repository.AreaMunicipalRepository;

@Service
public class AreaMunicipalService {
	@Autowired
    private AreaMunicipalRepository repository;

    @Autowired
    private CasoRepository casoRepository;

    public List<AreaResumenDTO> listarTodasConResumen() {
        List<AreaMunicipal> areas = repository.findAll();
        return areas.stream().map(area -> {
            List<Caso> casos = casoRepository.findByAreaId(area.getId());
            return new AreaResumenDTO(area, casos);
        }).collect(java.util.stream.Collectors.toList());
    }

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
