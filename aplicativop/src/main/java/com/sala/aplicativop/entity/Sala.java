package com.sala.aplicativop.entity;

import com.sala.aplicativop.dto.SalaDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity(name = "sala")
public class Sala implements Serializable {

    private static final long serialVersionUID = 22233311;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sala")
    private long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas;

    public Sala() {
    }

    public Sala(long id, String nome, String departamento, String descricao, Boolean status, List<Reserva> reservas) {
        this.id = id;
        this.nome = nome;
        this.departamento = departamento;
        this.descricao = descricao;
        this.status = status;
        this.reservas = reservas;
    }

    public Sala(SalaDTO dto) {
        this.nome = dto.nome();
        this.departamento = dto.departamento();
        this.descricao = dto.descricao();
        this.status = dto.status();
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

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
