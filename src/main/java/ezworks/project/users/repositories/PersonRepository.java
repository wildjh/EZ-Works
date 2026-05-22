package ezworks.project.users.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import ezworks.project.users.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    // Metodos personalizados para la entidad Person, si es necesario
    Optional<Person> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<Person> modifyData(Integer id);
    Optional<Person> findById(Integer id);
}
