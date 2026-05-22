package ezworks.project.jobmodule.repositories;

import ezworks.project.jobmodule.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // JpaRepository ya incluye métodos como findAll(), findById(), save(), deleteById()
}