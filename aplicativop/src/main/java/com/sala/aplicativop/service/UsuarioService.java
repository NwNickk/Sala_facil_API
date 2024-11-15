package com.sala.aplicativop.service;

import com.sala.aplicativop.dto.UsuarioDTO;
import com.sala.aplicativop.entity.Usuario;
import com.sala.aplicativop.exceptions.*;
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

    public Usuario saveUsuario(UsuarioDTO usuarioDTO){
        if (repository.existsByNome(usuarioDTO.nome())) {
            throw new NomeUsuarioInvalidoException();
        }
        if (repository.existsByEmail(usuarioDTO.email())) {
            throw new EmailInvalidoException();
        }
        if (repository.existsByTelefone(usuarioDTO.telefone())) {
            throw new TelefoneInvalidoException();
        }
        if (repository.existsByCpf(usuarioDTO.cpf())) {
            throw new CpfInvalidoException();
        }
        Usuario usuario = new Usuario(usuarioDTO);
        return repository.save(usuario);
    }

    public Usuario findById(long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(UsuarioNotFoundException::new);
        return usuario;
    }

    public Usuario updateUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuarioExistente = repository.findById(id)
                .orElseThrow(UsuarioNotFoundException::new);

        if (usuarioDTO.nome() != null && !usuarioDTO.nome().equals(usuarioExistente.getNome())) {
            if (repository.existsByNome(usuarioDTO.nome())) {
                throw new NomeUsuarioInvalidoException();
            }
            usuarioExistente.setNome(usuarioDTO.nome());
        }
        if (usuarioDTO.email() != null && !usuarioDTO.email().equals(usuarioExistente.getEmail())) {
            if (repository.existsByEmail(usuarioDTO.email())) {
                throw new EmailInvalidoException();
            }
            usuarioExistente.setEmail(usuarioDTO.email());
        }
        if (usuarioDTO.telefone() != null && !usuarioDTO.telefone().equals(usuarioExistente.getTelefone())) {
            if (repository.existsByTelefone(usuarioDTO.telefone())) {
                throw new TelefoneInvalidoException();
            }
            usuarioExistente.setTelefone(usuarioDTO.telefone());
        }
        if (usuarioDTO.cpf() != null && !usuarioDTO.cpf().equals(usuarioExistente.getCpf())) {
            if (repository.existsByCpf(usuarioDTO.cpf())) {
                throw new CpfInvalidoException();
            }
            usuarioExistente.setCpf(usuarioDTO.cpf());
        }
        return repository.save(usuarioExistente);
    }

    public void deleteUsuario(long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(UsuarioNotFoundException::new);
        repository.delete(usuario);
    }
}
