package com.sala.aplicativop.service;

import com.sala.aplicativop.dto.AuthenticationDTO;
import com.sala.aplicativop.dto.UsuarioDTO;
import com.sala.aplicativop.entity.Usuario;
import com.sala.aplicativop.exceptions.*;
import com.sala.aplicativop.repository.UserRepository;
import com.sala.aplicativop.repository.UsuarioRepository;
import com.sala.aplicativop.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    public List<Usuario> findAll(){
        return repository.findAll();
    }

    public Usuario saveUsuario(UsuarioDTO usuarioDTO){
        if (repository.existsByNome(usuarioDTO.nome())) {
            throw new NomeUsuarioInvalidoException();
        }
        if (userRepository.findByLogin(usuarioDTO.login()) != null) {
            throw new LoginAlreadyExistsException();
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
        String encryptedPassword = new BCryptPasswordEncoder().encode(usuarioDTO.senha());
        Usuario newUser = new Usuario(usuarioDTO);
        newUser.setSenha(encryptedPassword);

        return repository.save(newUser);
    }

    public String login(AuthenticationDTO authenticationDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(
                authenticationDTO.login(),
                authenticationDTO.senha()
        );

        var auth = authenticationManager.authenticate(usernamePassword);
        return tokenService.generateToken((Usuario) auth.getPrincipal());
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
        if (usuarioDTO.login() != null && !usuarioDTO.login().equals(usuarioExistente.getLogin())) {
            if (userRepository.findByLogin(usuarioDTO.login()) != null) {
                throw new LoginAlreadyExistsException();
            }
            usuarioExistente.setLogin(usuarioDTO.login());
        }
        if (usuarioDTO.email() != null && !usuarioDTO.email().equals(usuarioExistente.getEmail())) {
            if (repository.existsByEmail(usuarioDTO.email())) {
                throw new EmailInvalidoException();
            }
            usuarioExistente.setEmail(usuarioDTO.email());
        }
        if (usuarioDTO.senha() != null && !usuarioDTO.senha().isBlank()) {
            String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioDTO.senha());
            usuarioExistente.setSenha(senhaCriptografada);
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
        if (usuarioDTO.role() != null && !usuarioDTO.role().equals(usuarioExistente.getRole())) {
            usuarioExistente.setRole(usuarioDTO.role());
        }
        return repository.save(usuarioExistente);
    }

    public void deleteUsuario(long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(UsuarioNotFoundException::new);
        repository.delete(usuario);
    }
}
