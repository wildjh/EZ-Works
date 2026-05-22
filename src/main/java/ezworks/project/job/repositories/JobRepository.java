package ezworks.project.jobmodule.repositories;

import ezworks.project.jobmodule.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    List<Job> findByStatus(Integer status);

    // Busca empleos por categoría
    List<Job> findByCategoryId(Integer categoryId);
}