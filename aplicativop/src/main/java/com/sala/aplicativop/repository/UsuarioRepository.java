package com.sala.aplicativop.repository;

import com.sala.aplicativop.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByNome(String nome);
    boolean existsByEmail(String email);
    boolean existsByTelefone(String telefone);
    boolean existsByCpf(String cpf);
}
