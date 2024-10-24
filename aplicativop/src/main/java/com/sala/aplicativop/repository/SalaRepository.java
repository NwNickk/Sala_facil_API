package com.sala.aplicativop.repository;

import com.sala.aplicativop.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long>{

    boolean existsByIdAndStatus(long idSala, boolean status);

}
