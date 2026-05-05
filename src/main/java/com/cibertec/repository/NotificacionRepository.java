package com.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.model.Notificacion;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Integer>{
	List<Notificacion> findByUsuarioIdOrderByCreadoEnDesc(Integer usuarioId);
    
    long countByUsuarioIdAndLeidaFalse(Integer usuarioId);
}
