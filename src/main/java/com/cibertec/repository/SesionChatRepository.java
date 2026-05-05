package com.cibertec.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.enums.Modelos_enum.EstadoChat;
import com.cibertec.model.SesionChat;

@Repository
public interface SesionChatRepository extends JpaRepository<SesionChat, Integer>{
	Optional<SesionChat> findByUsuarioIdAndEstado(Integer usuarioId, EstadoChat estado);
    
    List<SesionChat> findByUsuarioIdOrderByActualizadoEnDesc(Integer usuarioId);
}
