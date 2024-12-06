package com.sala.aplicativop.service;

import com.sala.aplicativop.dto.ReservaDTO;
import com.sala.aplicativop.entity.Reserva;
import com.sala.aplicativop.entity.Sala;
import com.sala.aplicativop.entity.Usuario;
import com.sala.aplicativop.exceptions.*;
import com.sala.aplicativop.repository.ReservaRepository;
import com.sala.aplicativop.repository.SalaRepository;
import com.sala.aplicativop.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Reserva saveReserva(ReservaDTO reservaDTO) {
        Usuario usuario = usuarioRepository.findById(reservaDTO.usuarioId())
                .orElseThrow(UsuarioNotFoundException::new);

        Sala sala = salaRepository.findById(reservaDTO.salaId())
                .orElseThrow(SalaNotFoundException::new);

        if (reservaDTO.dataReserva().isBefore(LocalDateTime.now())) {
            throw new DataPassadaException();
        }
        if (reservaDTO.dataReserva().isAfter(LocalDateTime.now().plusDays(30))) {
            throw new MuitaAntecedenciaException();
        }
        if (reservaRepository.existsByUsuarioAndDataReserva(usuario, reservaDTO.dataReserva())) {
            throw new JaTemReservaException();
        }
        if (!sala.getStatus()) {
            throw new SalaIndisponivelException();
        }
        if (reservaRepository.existsBySalaAndDataReserva(sala, reservaDTO.dataReserva())) {
            throw new SalaReservadaException();
        }
        Reserva novaReserva = new Reserva(reservaDTO, usuario, sala);
        return reservaRepository.save(novaReserva);
    }

    public List<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }

    public List<Reserva> findReservasByUsuarioId(long userId) {
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(UsuarioNotFoundException::new);
        return reservaRepository.findAllByUsuario(usuario);
    }

    public  List<Reserva> findReservasBySalaId(long salaId) {
        Sala sala = salaRepository.findById(salaId)
                .orElseThrow(SalaNotFoundException::new);
        return reservaRepository.findAllBySala(sala);
    }
    public List<Reserva> getReservasByData(String dataReservaString) {
        LocalDate dataReserva = LocalDate.parse(dataReservaString, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDateTime startOfDay = dataReserva.atStartOfDay();
        LocalDateTime endOfDay = dataReserva.atTime(23, 59, 59);

        List<Reserva> reservas = reservaRepository.findByDataReservaBetween(startOfDay, endOfDay);

        if (reservas.isEmpty()) {
            throw new DataReservaNotFoundException();
        }
        return reservas;
    }

    public Reserva getReservaById(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(ReservaNotFoundException::new);
        return reserva;
    }

    public void deleteReserva(long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(ReservaNotFoundException::new);
        if (reserva.getDataReserva().isBefore(LocalDateTime.now())) {
            throw new DataPassadaException();
        }
        reservaRepository.delete(reserva);
    }

    public Reserva updateReserva(Long id, ReservaDTO novaReservaDTO) {
        Reserva reservaExistente = reservaRepository.findById(id)
                .orElseThrow(ReservaNotFoundException::new);
        if (reservaExistente.getDataReserva().isBefore(LocalDateTime.now())) {
            throw new ReservaJaOcorridaException();
        }
        if (novaReservaDTO.dataReserva() != null && novaReservaDTO.dataReserva().isBefore(LocalDateTime.now())) {
            throw new DataPassadaException();
        }
        if (novaReservaDTO.dataReserva() != null &&
                novaReservaDTO.dataReserva().isAfter(LocalDateTime.now().plusDays(30))) {
            throw new MuitaAntecedenciaException();
        }
        if (novaReservaDTO.usuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(novaReservaDTO.usuarioId())
                    .orElseThrow(UsuarioNotFoundException::new);

            if (reservaRepository.existsByUsuarioAndDataReserva(usuario,
                    novaReservaDTO.dataReserva() != null ? novaReservaDTO.dataReserva() : reservaExistente.getDataReserva())) {
                throw new JaTemReservaException();
            }
            reservaExistente.setUsuario(usuario);
        }

        if (novaReservaDTO.salaId() != null) {
            Sala sala = salaRepository.findById(novaReservaDTO.salaId())
                    .orElseThrow(SalaNotFoundException::new);

            if (!salaRepository.existsByIdAndStatus(novaReservaDTO.salaId(), true)) {
                throw new SalaIndisponivelException();
            }
            if (reservaRepository.existsBySalaAndDataReserva(sala,
                    novaReservaDTO.dataReserva() != null ? novaReservaDTO.dataReserva() : reservaExistente.getDataReserva())) {
                throw new SalaReservadaException();
            }
            reservaExistente.setSala(sala);
        }

        if (novaReservaDTO.dataReserva() != null) {
            reservaExistente.setDataReserva(novaReservaDTO.dataReserva());
        }
        if (novaReservaDTO.status() != null) {
            reservaExistente.setStatus(novaReservaDTO.status());
        }
        reservaExistente.setDataPedido(LocalDateTime.now());

        return reservaRepository.save(reservaExistente);
    }
}
