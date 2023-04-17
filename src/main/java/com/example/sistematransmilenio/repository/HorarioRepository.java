package com.example.sistematransmilenio.repository;

import com.example.sistematransmilenio.model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    @Query(value = "select h FROM Horario h INNER JOIN Ruta r ON h.rutaHorario = r INNER JOIN Bus b ON h.busHorario = b")
    List<Horario> getHorarioByRutaWithBus(Long id);
}
