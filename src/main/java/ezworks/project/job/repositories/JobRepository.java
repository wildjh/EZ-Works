package ezworks.project.job.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ezworks.project.job.entities.Job;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    List<Job> findByStatus(Integer status);

    // Busca empleos por categoría
    List<Job> findByCategoryId(Integer categoryId);
}