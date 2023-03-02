package com.example.sistematransmilenio.repository;

import com.example.sistematransmilenio.model.Conductor;
import com.example.sistematransmilenio.service.ConductorService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConductorRepository extends JpaRepository<Conductor, Long> {

}
