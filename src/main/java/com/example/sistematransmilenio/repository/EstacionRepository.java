package com.example.sistematransmilenio.repository;

import com.example.sistematransmilenio.model.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacionRepository extends JpaRepository<Estacion, Long> {
}
