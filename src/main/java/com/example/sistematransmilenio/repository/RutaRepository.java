package com.example.sistematransmilenio.repository;

import com.example.sistematransmilenio.model.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Long> {

    Ruta findRutaByNombreRuta(String nombreRuta);


}
