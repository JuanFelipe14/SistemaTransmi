package com.example.sistematransmilenio.repository;

import com.example.sistematransmilenio.model.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConductorRepository extends JpaRepository<Conductor, Long> {
    @Query("SELECT c FROM Conductor c WHERE c.nombre LIKE concat(:text, '%')")
    List<Conductor> findConductorByNameStartingWith(@Param("text") String text);

    Conductor findConductorByCedula(int cedula);
}
