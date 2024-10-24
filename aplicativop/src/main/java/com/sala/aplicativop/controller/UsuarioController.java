package com.sala.aplicativop.controller;

import com.sala.aplicativop.entity.Usuario;
import com.sala.aplicativop.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "usuario")

public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<Usuario> getUsuarios(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable long id) {
        try {
            Usuario usuario = service.findById(id);
            return ResponseEntity.ok(usuario);
        }  catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public Usuario saveUsuario(@RequestBody Usuario usuario){
        Usuario usuarioSalvo = service.saveUsuario(usuario);
        return usuarioSalvo;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarUsuario(@PathVariable long id, @RequestBody Usuario novoUsuario) {
        try {
            Usuario usuarioEditado  = service.updateUsuario(id, novoUsuario);
            return ResponseEntity.ok(usuarioEditado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable Long id) {
        try {
            service.deleteUsuario(id);
            return ResponseEntity.ok("Usuário deletado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
