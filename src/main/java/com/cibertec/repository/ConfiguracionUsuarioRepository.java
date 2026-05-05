package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cibertec.model.ConfiguracionUsuario;

import java.util.Optional;

@Repository
public interface ConfiguracionUsuarioRepository extends JpaRepository<ConfiguracionUsuario, Integer> {
	
	Optional<ConfiguracionUsuario> findByUsuarioId(Integer usuarioId);
}
