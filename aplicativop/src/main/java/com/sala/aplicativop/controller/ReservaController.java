package com.sala.aplicativop.controller;

import com.sala.aplicativop.entity.Reserva;
import com.sala.aplicativop.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<?> criarReserva(@RequestBody Reserva reserva) {
        try {
            Reserva novaReserva = reservaService.saveReserva(reserva);
            return ResponseEntity.ok(novaReserva);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> listarReservas() {
        List<Reserva> reservas = reservaService.getAllReservas();
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/{userId}/usuario")
    public ResponseEntity<?> getReservasByUsuario(@PathVariable int userId) {
        try {
            List<Reserva> reservas = reservaService.findReservasByUsuarioId(userId);
            return ResponseEntity.ok(reservas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{salaId}/sala")
    public ResponseEntity<?> getReservasBySala(@PathVariable int salaId) {
        try {
            List<Reserva> reservas = reservaService.findReservasBySalaId(salaId);
            return ResponseEntity.ok(reservas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarReservaPorId(@PathVariable long id) {
        try {
            Reserva reserva = reservaService.getReservaById(id);
            return ResponseEntity.ok(reserva);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarReserva(@PathVariable long id) {
        try {
            reservaService.deleteReserva(id);
            return ResponseEntity.ok("Reserva deletada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarReserva(@PathVariable Long id, @RequestBody Reserva novaReserva) {
        try {
            Reserva reservaAtualizada = reservaService.updateReserva(id, novaReserva);
            return ResponseEntity.ok(reservaAtualizada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
