package com.sala.aplicativop.service;

import com.sala.aplicativop.dto.SalaDTO;
import com.sala.aplicativop.entity.Sala;
import com.sala.aplicativop.exceptions.NomeSalaInvalidoException;
import com.sala.aplicativop.exceptions.SalaNotFoundException;
import com.sala.aplicativop.exceptions.SalaReservadaException;
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

    public Sala saveSala(SalaDTO salaDTO){
        if (repository.existsByNome(salaDTO.nome())) {
            throw new SalaReservadaException();
        }
        Sala sala = new Sala(salaDTO);
        return repository.save(sala);
    }

    public Sala findById(long id) {
        Sala sala = repository.findById(id)
                .orElseThrow(SalaNotFoundException::new);
        return sala;
    }

    public Sala updateSala(Long id, SalaDTO salaDTO) {
        Sala salaExistente = repository.findById(id)
                .orElseThrow(SalaNotFoundException::new);

        if (salaDTO.nome() != null && !salaDTO.nome().equals(salaExistente.getNome())) {
            if (repository.existsByNome(salaDTO.nome())) {
                throw new NomeSalaInvalidoException();
            }
            salaExistente.setNome(salaDTO.nome());
        }
        if (salaDTO.departamento() != null) {
            salaExistente.setDepartamento(salaDTO.departamento());
        }
        if (salaDTO.descricao() != null) {
            salaExistente.setDescricao(salaDTO.descricao());
        }
        if (salaDTO.status() != null) {
            salaExistente.setStatus(salaDTO.status());
        }
        return repository.save(salaExistente);
    }

    public void deleteSala(long id) {
        Sala sala = repository.findById(id)
                        .orElseThrow(SalaNotFoundException::new);
        repository.delete(sala);
    }
}
