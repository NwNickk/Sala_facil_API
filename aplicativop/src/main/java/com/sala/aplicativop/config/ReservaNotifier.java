package com.sala.aplicativop.config;

import com.sala.aplicativop.entity.Reserva;
import com.sala.aplicativop.repository.ReservaRepository;
import com.sala.aplicativop.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaNotifier {
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0/5 * * * ?")
    public void notifyUsers() {
        LocalDateTime startOfTomorrow = LocalDateTime.now().plusDays(1).toLocalDate().atStartOfDay();
        LocalDateTime endOfTomorrow = startOfTomorrow.plusDays(1).minusSeconds(1);

        List<Reserva> reservas = reservaRepository.findByDataReservaBetween(startOfTomorrow, endOfTomorrow);

        for (Reserva reserva : reservas) {
            String email = reserva.getUsuario().getEmail();
            String subject = "Lembrete: Reserva próxima";
            String body = "Olá " + reserva.getUsuario().getNome() + ",\n\n"
                    + "A reserva da sala " + reserva.getSala().getNome()
                    + " está marcada para amanhã (" + reserva.getDataReserva() + ").\n\n"
                    + "Atenciosamente,\nSistema de Reservas";

            emailService.sendEmail(email, subject, body);
        }
    }
}
