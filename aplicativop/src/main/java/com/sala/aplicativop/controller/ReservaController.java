package com.sala.aplicativop.controller;

import com.sala.aplicativop.dto.ReservaDTO;
import com.sala.aplicativop.entity.Reserva;
import com.sala.aplicativop.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<Reserva> criarReserva(@Valid @RequestBody ReservaDTO reservaDTO) {
        Reserva novaReserva = reservaService.saveReserva(reservaDTO);
        return new ResponseEntity<>(novaReserva, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> listarReservas() {
        List<Reserva> reservas = reservaService.getAllReservas();
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/data/{dataReserva}")
    public List<Reserva> getReservasByData(@PathVariable String dataReserva) {
        return reservaService.getReservasByData(dataReserva);
    }

    @GetMapping("/{userId}/usuarios")
    public ResponseEntity<List<Reserva>> findReservasByUsuarioId(@PathVariable long userId) {
        List<Reserva> reservas = reservaService.findReservasByUsuarioId(userId);
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/{salaId}/salas")
    public ResponseEntity<List<Reserva>> getReservasBySala(@PathVariable long salaId) {
        List<Reserva> reservas = reservaService.findReservasBySalaId(salaId);
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscarReservaPorId(@PathVariable long id) {
        Reserva reserva = reservaService.getReservaById(id);
        return ResponseEntity.ok(reserva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarReserva(@PathVariable long id) {
        reservaService.deleteReserva(id);
        return new ResponseEntity<>("Reserva deletada com sucesso.", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> atualizarReserva(@PathVariable Long id, @RequestBody ReservaDTO novaReservaDTO) {
        Reserva reservaAtualizada = reservaService.updateReserva(id, novaReservaDTO);
        return new ResponseEntity<>(reservaAtualizada, HttpStatus.OK);
    }
}
