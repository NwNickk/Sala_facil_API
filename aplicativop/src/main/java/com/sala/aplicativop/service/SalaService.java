package com.sala.aplicativop.service;

import com.sala.aplicativop.entity.Sala;
import com.sala.aplicativop.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {

    @Autowired
    private SalaRepository repository;

    public List<Sala> findAll(){
        return repository.findAll();
    }

    public Sala saveSala(Sala sala){
        Sala salaSalva = repository.save(sala);
        return salaSalva;
    }

    public Sala findById(long id) throws Exception {
        Sala sala = repository.findById(id)
                .orElseThrow(() -> new Exception("Sala não encontrada"));
        return sala;
    }

    public Sala updateSala(Long id, Sala sala) throws Exception {
        Sala salaExistente = repository.findById(id)
                .orElseThrow(() -> new Exception("Sala não encontrada"));

        salaExistente.setNome(sala.getNome() != null ? sala.getNome() : salaExistente.getNome());
        salaExistente.setDepartamento(sala.getDepartamento() != null ? sala.getDepartamento() : salaExistente.getDepartamento());
        salaExistente.setDescricao(sala.getDescricao() != null ? sala.getDescricao() : salaExistente.getDescricao());
        salaExistente.setStatus(sala.getStatus() != null ? sala.getStatus() : salaExistente.getStatus());
        
        Sala salaSalva = repository.save(salaExistente);
        return salaSalva;
    }

    public void deleteSala(long id) throws Exception {
        Sala sala = repository.findById(id)
                        .orElseThrow(() -> new Exception("Sala não encontrada"));
        repository.delete(sala);
    }
}
