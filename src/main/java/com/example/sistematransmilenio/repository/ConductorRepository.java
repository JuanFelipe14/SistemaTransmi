package com.example.sistematransmilenio.repository;

import com.example.sistematransmilenio.model.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ConductorRepository extends JpaRepository<Conductor, Long> {
}
