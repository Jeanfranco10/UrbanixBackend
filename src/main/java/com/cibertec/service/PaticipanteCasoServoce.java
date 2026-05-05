package com.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.model.ParticipanteCaso;
import com.cibertec.repository.ParticipanteRepository;

@Service
public class PaticipanteCasoServoce {
	
	@Autowired
    private ParticipanteRepository repository;

    public ParticipanteCaso unirseACaso(ParticipanteCaso participante) {
        return repository.save(participante);
    }

    public List<ParticipanteCaso> listarPorCaso(Integer casoId) {
        return repository.findByCasoId(casoId);
    }
}
