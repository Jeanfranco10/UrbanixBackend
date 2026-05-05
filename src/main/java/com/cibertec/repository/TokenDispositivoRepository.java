package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cibertec.model.TokenDispositivo;
import java.util.Optional;

public interface TokenDispositivoRepository extends JpaRepository<TokenDispositivo, Integer>{
	
	Optional<TokenDispositivo> findByToken(String token);
    
    void deleteByUsuarioId(Integer usuarioId);
}
