package ezworks.project.jobmodule.repositories;

import ezworks.project.jobmodule.entities.Matching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchingRepository extends JpaRepository<Matching, Integer> {

    // Para buscar todos los emparejamientos de un trabajo específico
    List<Matching> findByJobId(Integer jobId);

    // Para buscar los emparejamientos de un empleado específico
    List<Matching> findByEmployeeId(Integer employeeId);
}