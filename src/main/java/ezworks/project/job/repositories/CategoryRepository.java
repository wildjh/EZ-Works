package ezworks.project.job.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ezworks.project.job.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // JpaRepository ya incluye métodos como findAll(), findById(), save(), deleteById()
}