package com.sala.aplicativop.controller;

import com.sala.aplicativop.dto.AuthenticationDTO;
import com.sala.aplicativop.dto.LoginResponseDTO;
import com.sala.aplicativop.dto.UsuarioDTO;
import com.sala.aplicativop.entity.Usuario;
import com.sala.aplicativop.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<Usuario> getUsuarios(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable long id) {
        Usuario usuario = service.findById(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        Usuario novoUsuario = service.saveUsuario(usuarioDTO);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        String token = service.login(data);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> editarUsuario(@PathVariable long id, @RequestBody UsuarioDTO novoUsuarioDTO) {
        Usuario usuarioEditado  = service.updateUsuario(id, novoUsuarioDTO);
        return new ResponseEntity<>(usuarioEditado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id) {
        service.deleteUsuario(id);
        return new ResponseEntity<>("Usuário deletado com sucesso.", HttpStatus.OK);
    }
}
