package repository;

import model.Conductor;
import model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ConductorRepository extends CrudRepository<Conductor, Long> {
}
