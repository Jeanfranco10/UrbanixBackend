package com.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.model.ParticipanteCaso;

@Repository
public interface ParticipanteRepository extends JpaRepository<ParticipanteCaso, Integer> {
	
	List<ParticipanteCaso> findByCasoId(Integer casoId);
    
    List<ParticipanteCaso> findByUsuarioId(Integer usuarioId);
	
}
