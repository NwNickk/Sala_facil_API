package com.sala.aplicativop.repository;

import com.sala.aplicativop.entity.Reserva;
import com.sala.aplicativop.entity.Sala;
import com.sala.aplicativop.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Reserva r " +
            "WHERE r.usuario = :usuario AND r.dataReserva = :dataReserva")
    boolean existsByUsuarioAndDataReserva(Usuario usuario, LocalDateTime dataReserva);

    boolean existsBySalaAndDataReserva(Sala sala, LocalDateTime dataReserva);

    List<Reserva> findAllByUsuario(Usuario usuario);

    List<Reserva> findAllBySala(Sala sala);

    List<Reserva> findByDataReservaBetween(LocalDateTime start, LocalDateTime end);

}
