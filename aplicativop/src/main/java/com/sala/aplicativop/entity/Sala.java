package com.sala.aplicativop.entity;

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
    private Integer status;

    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas;

    public Sala() {
    }

    public Sala(long id, String nome, String departamento, String descricao, int status) {
        this.id = id;
        this.nome = nome;
        this.departamento = departamento;
        this.descricao = descricao;
        this.status = status;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
