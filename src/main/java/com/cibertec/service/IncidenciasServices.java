package com.cibertec.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.cibertec.model.Caso;
import com.cibertec.repository.CasoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.model.Incidencia;
import com.cibertec.repository.IncidenciaRepository;

@Service
public class IncidenciasServices {
	@Autowired
    private IncidenciaRepository repository;

    @Autowired
    private CasoRepository casoRepository;

    public List<Incidencia> listarPorUsuario(Integer usuarioId) {
        return repository.findByUsuarioIdOrderByCreadoEnDesc(usuarioId);
    }

    public Incidencia registrar(Incidencia incidencia) {

        if (incidencia.getCodigo() == null || incidencia.getCodigo().isBlank()) {
            String codigo = "INC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            incidencia.setCodigo(codigo);
        }
        Incidencia saved = repository.save(incidencia);

        // Actualizar total_reportes del caso si tiene uno asociado
        if (saved.getCaso() != null) {
            casoRepository.findById(saved.getCaso().getId()).ifPresent(caso -> {
                caso.setTotalReportes(caso.getTotalReportes() + 1);
                casoRepository.save(caso);
            });
        }
        return saved;
    }
    
    public List<Incidencia> listarTodas() {
        return repository.findAll();
    }
    
    public Optional<Incidencia> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public List<Incidencia> listarPorCaso(Integer casoId) {
        return repository.findByCasoId(casoId);
    }
}
