package com.sala.aplicativop.entity;

import com.sala.aplicativop.dto.UsuarioDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1112233;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "cpf")
    private String cpf;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas;

    public Usuario() {
    }

    public Usuario(long id, String nome, String email, String telefone, String cpf, List<Reserva> reservas) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.reservas = reservas;
    }

    public Usuario(UsuarioDTO dto) {
        this.nome = dto.nome();
        this.email = dto.email();
        this.telefone = dto.telefone();
        this.cpf = dto.cpf();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
