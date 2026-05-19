package com.cibertec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.model.Usuario;
import com.cibertec.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
	@Autowired
    private UsuarioService service;

    @GetMapping  public List<Usuario> listar() {
        return service.listarTodos();
    }
    @GetMapping("/correo/{correo}")
    public ResponseEntity<Usuario> obtener(@PathVariable String correo) {
        Usuario user = service.buscarPorCorreo(correo);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) {
        return service.guardar(usuario);
    }
    @PatchMapping("/{id}/rol")
    public ResponseEntity<Usuario> cambiarRol(@PathVariable Integer id, @RequestBody Usuario datos) {
        Usuario user = service.buscarPorId(id);
        if (user != null && datos.getRol() != null) {
            user.setRol(datos.getRol());
            return ResponseEntity.ok(service.guardar(user));
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> editar(@PathVariable Integer id, @RequestBody Usuario datos) {
        Usuario user = service.buscarPorId(id);
        if (user != null) {
            if (datos.getNombre() != null) user.setNombre(datos.getNombre());
            if (datos.getTelefono() != null) user.setTelefono(datos.getTelefono());
            if (datos.getContrasenaHash() != null) user.setContrasenaHash(datos.getContrasenaHash());
            if (datos.getActivo() != null) user.setActivo(datos.getActivo()); // AGREGAR
            if (datos.getArea() != null) user.setArea(datos.getArea());      // AGREGAR

            return ResponseEntity.ok(service.guardar(user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/login")
    public ResponseEntity<Usuario> login(@RequestParam String correo, @RequestParam String clave) {
        Usuario user = service.buscarPorCorreo(correo);
        if (user != null && user.getContrasenaHash().equals(clave))
            return ResponseEntity.ok(user);
        return ResponseEntity.status(401).build();
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Integer id) {
        Usuario user = service.buscarPorId(id);
        return (user != null)
                ? ResponseEntity.ok(user)
                : ResponseEntity.notFound().build();
        }
	
}
