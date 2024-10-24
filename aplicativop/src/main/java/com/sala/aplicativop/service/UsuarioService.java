package com.sala.aplicativop.service;

import com.sala.aplicativop.entity.Usuario;
import com.sala.aplicativop.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> findAll(){
        return repository.findAll();
    }

    public Usuario saveUsuario(Usuario usuario){
        Usuario usuarioSalvo = repository.save(usuario);
        return usuarioSalvo;
    }

    public Usuario findById(long id) throws Exception {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new Exception("Usuário não encontrado"));
        return usuario;
    }

    public Usuario updateUsuario(Long id, Usuario usuario) throws Exception {
        Usuario usuarioExistente = repository.findById(id)
                .orElseThrow(() -> new Exception("Usuário não encontrado"));

        usuarioExistente.setNome(usuario.getNome() != null ? usuario.getNome() : usuarioExistente.getNome());
        usuarioExistente.setEmail(usuario.getEmail() != null ? usuario.getEmail() : usuarioExistente.getEmail());
        usuarioExistente.setPhone(usuario.getPhone() != null ? usuario.getPhone() : usuarioExistente.getPhone());
        usuarioExistente.setCpf(usuario.getCpf() != null ? usuario.getCpf() : usuarioExistente.getCpf());

        Usuario usuarioSalvo = repository.save(usuarioExistente);
        return usuarioSalvo;
    }

    public void deleteUsuario(long id) throws Exception {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new Exception("Usuário não encontrado"));
        repository.delete(usuario);
    }
}
