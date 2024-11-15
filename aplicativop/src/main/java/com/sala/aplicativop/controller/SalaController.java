package com.sala.aplicativop.controller;

import com.sala.aplicativop.dto.SalaDTO;
import com.sala.aplicativop.entity.Sala;
import com.sala.aplicativop.service.SalaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "salas")
public class SalaController {

    @Autowired
    private SalaService service;

    @GetMapping
    public List<Sala> getSalas(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> getSalaById(@PathVariable long id) {
        Sala sala = service.findById(id);
        return ResponseEntity.ok(sala);
    }

    @PostMapping
    public ResponseEntity<Sala> criarSala(@RequestBody @Valid SalaDTO salaDTO) {
        Sala novaSala = service.saveSala(salaDTO);
        return new ResponseEntity<>(novaSala, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sala> editarSala(@PathVariable long id, @RequestBody SalaDTO novaSalaDTO) {
        Sala salaEditada  = service.updateSala(id, novaSalaDTO);
        return new ResponseEntity<>(salaEditada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarSala(@PathVariable Long id) {
        service.deleteSala(id);
        return new ResponseEntity<>("Sala deletada com sucesso.", HttpStatus.OK);
    }
}
