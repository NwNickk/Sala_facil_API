package com.sala.aplicativop.service;

import com.sala.aplicativop.entity.Reserva;
import com.sala.aplicativop.entity.Sala;
import com.sala.aplicativop.entity.Usuario;
import com.sala.aplicativop.repository.ReservaRepository;
import com.sala.aplicativop.repository.SalaRepository;
import com.sala.aplicativop.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Reserva saveReserva(Reserva reserva) throws Exception {

        // Verifica se o usuário existe
        Usuario usuario = usuarioRepository.findById(reserva.getUsuario().getId())
                .orElseThrow(() -> new Exception("Usuário não encontrado"));

        // Verifica se a sala existe
        Sala sala = salaRepository.findById(reserva.getSala().getId())
                .orElseThrow(() -> new Exception("Sala não encontrada"));

        // Verifica se a data da reserva já não passou
        if (reserva.getDataReserva().isBefore(LocalDateTime.now())) {
            throw new Exception("Não é possível reservar uma data passada.");
        }

        // Verifica se a data da reserva é no máximo 30 dias no futuro
        if (reserva.getDataReserva().isAfter(LocalDateTime.now().plusDays(30))) {
            throw new Exception("Não é permitido reservar com mais de 30 dias de antecedência.");
        }

        // Verifica se o usuário já tem uma reserva no mesmo horário
        boolean usuarioJaReservou = reservaRepository.existsByUsuarioAndDataReserva(
                reserva.getUsuario(), reserva.getDataReserva()
        );

        if (usuarioJaReservou) {
            throw new Exception("O usuário já possui uma reserva para o mesmo horário.");
        }

        // Verifica se a sala está ativa
        if (!salaRepository.existsByIdAndStatus(reserva.getSala().getId(), 1)) {
            throw new Exception("A sala não está disponível para reserva.");
        }

        // Verifica se a sala já está reservada para o mesmo horário
        if (reservaRepository.existsBySalaAndDataReserva(reserva.getSala(), reserva.getDataReserva())) {
            throw new Exception("A sala já está reservada para esse dia e horário.");
        }

        // Atualiza os objetos reserva com o usuário e a sala
        reserva.setUsuario(usuario);
        reserva.setSala(sala);

        // Define automaticamente a data do pedido como a data e hora atual
        reserva.setDataPedido(LocalDateTime.now());

        return reservaRepository.save(reserva);
    }

    public List<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }

    // Método para listar todas as reservas de um usuário
    public List<Reserva> findReservasByUsuarioId(long userId) throws Exception {
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new Exception("Usuário não encontrado"));
        return reservaRepository.findAllByUsuario(usuario);
    }

    // Método para listar todas as reservas de uma sala
    public  List<Reserva> findReservasBySalaId(long salaId) throws Exception {
        Sala sala = salaRepository.findById(salaId)
                .orElseThrow(() -> new Exception("Sala não encontrada"));
        return reservaRepository.findAllBySala(sala);
    }

    public Reserva getReservaById(Long id) throws Exception {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new Exception("Reserva não encontrada"));
        return reserva;
    }

    public void deleteReserva(long id) throws Exception {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new Exception("Reserva não encontrada"));

        // Verifica se a data da reserva já passou
        if (reserva.getDataReserva().isBefore(LocalDateTime.now())) {
            throw new Exception("Não é possível deletar uma reserva que já ocorreu.");
        }
        reservaRepository.delete(reserva);
    }

    public Reserva updateReserva(Long id, Reserva novaReserva) throws Exception {
        Reserva reservaExistente = reservaRepository.findById(id)
                .orElseThrow(() -> new Exception("Reserva não encontrada"));

        // Verifica se a data da reserva já passou
        if (reservaExistente.getDataReserva().isBefore(LocalDateTime.now())) {
            throw new Exception("Não é possível atualizar uma reserva que já ocorreu.");
        }

        // Verifica se a nova data da reserva é no máximo 30 dias no futuro
        if (novaReserva.getDataReserva() != null &&
                novaReserva.getDataReserva().isAfter(LocalDateTime.now().plusDays(30))) {
            throw new Exception("Não é permitido reservar com mais de 30 dias de antecedência.");
        }

        // Se for atualizar o usuário, verifica se o novo usuário existe
        if (novaReserva.getUsuario() != null) {
            Usuario usuario = usuarioRepository.findById(novaReserva.getUsuario().getId())
                    .orElseThrow(() -> new Exception("Usuário não encontrado"));

            // Verifica se o usuário já tem uma reserva para o mesmo horário
            boolean usuarioJaReservou = reservaRepository.existsByUsuarioAndDataReserva(
                    novaReserva.getUsuario(), novaReserva.getDataReserva() != null ? novaReserva.getDataReserva() : reservaExistente.getDataReserva());

            if (usuarioJaReservou) {
                throw new Exception("O usuário já possui uma reserva para o mesmo horário.");
            }

            // Atualiza o usuário
            reservaExistente.setUsuario(novaReserva.getUsuario());
        }

        // Se for atualizar a sala, verifica se a nova sala existe
        if (novaReserva.getSala() != null) {
            Sala sala = salaRepository.findById(novaReserva.getSala().getId())
                    .orElseThrow(() -> new Exception("Sala não encontrada"));

            // Verifica se a sala está ativa
            if (!salaRepository.existsByIdAndStatus(novaReserva.getSala().getId(), 1)) {
                throw new Exception("A sala não está disponível para reserva.");
            }

            // Verifica se a sala já está reservada para o mesmo horário
            boolean salaJaReservada = reservaRepository.existsBySalaAndDataReserva(
                    novaReserva.getSala(), novaReserva.getDataReserva() != null ? novaReserva.getDataReserva() : reservaExistente.getDataReserva());

            if (salaJaReservada) {
                throw new Exception("A sala já está reservada para esse dia e horário.");
            }

            // Atualiza a sala
            reservaExistente.setSala(novaReserva.getSala());
        }

        // Atualiza apenas os campos que foram fornecidos
        if (novaReserva.getDataReserva() != null) {
            reservaExistente.setDataReserva(novaReserva.getDataReserva());
        }
        if (novaReserva.getSala() != null) {
            reservaExistente.setSala(novaReserva.getSala());
        }
        if (novaReserva.getUsuario() != null) {
            reservaExistente.setUsuario(novaReserva.getUsuario());
        }
        if (novaReserva.getStatus() != null) {
            reservaExistente.setStatus(novaReserva.getStatus());
        }

        reservaExistente.setDataPedido(LocalDateTime.now());

        return reservaRepository.save(reservaExistente);
    }

}
