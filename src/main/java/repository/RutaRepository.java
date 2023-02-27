package repository;

import model.Horario;
import model.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RutaRepository extends CrudRepository<Ruta, Long> {
}
