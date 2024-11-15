package com.sala.aplicativop.entity;

import com.sala.aplicativop.dto.ReservaDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "reserva")
public class Reserva implements Serializable {

    private static final long serialVersionUID = 11233311;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private long idReserva;

    @Column(name = "data_reserva")
    private LocalDateTime dataReserva;

    @Column(name = "data_pedido")
    private LocalDateTime dataPedido;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Reserva() {
    }

    public Reserva(long idReserva, LocalDateTime dataReserva, LocalDateTime dataPedido, Boolean status, Sala sala, Usuario usuario) {
        this.idReserva = idReserva;
        this.dataReserva = dataReserva;
        this.dataPedido = dataPedido;
        this.status = status;
        this.sala = sala;
        this.usuario = usuario;
    }

    public Reserva(ReservaDTO dto,Usuario usuario, Sala sala) {
        this.dataReserva = dto.dataReserva();
        this.dataPedido = LocalDateTime.now();
        this.status = dto.status();
        this.usuario = usuario;
        this.sala = sala;
    }

    public long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(long idReserva) {
        this.idReserva = idReserva;
    }

    public LocalDateTime getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDateTime dataReserva) {
        this.dataReserva = dataReserva;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
