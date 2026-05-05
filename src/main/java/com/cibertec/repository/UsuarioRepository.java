package com.cibertec.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.enums.Modelos_enum.RolUsuario;
import com.cibertec.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByCorreo(String correo);
    
    List<Usuario> findByRol(RolUsuario rol);
    
    boolean existsByCorreo(String correo);
}
