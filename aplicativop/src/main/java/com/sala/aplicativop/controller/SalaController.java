package com.sala.aplicativop.controller;

import com.sala.aplicativop.entity.Sala;
import com.sala.aplicativop.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<?> getSalaById(@PathVariable long id) {
        try {
            Sala sala = service.findById(id);
            return ResponseEntity.ok(sala);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public Sala saveSala(@RequestBody Sala sala){
        Sala salaSalva = service.saveSala(sala);
        return salaSalva;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarSala(@PathVariable long id, @RequestBody Sala novaSala) {
        try {
           Sala salaEditada  = service.updateSala(id, novaSala);
            return ResponseEntity.ok(salaEditada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarSala(@PathVariable Long id) {
        try {
            service.deleteSala(id);
            return ResponseEntity.ok("Sala deletada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
