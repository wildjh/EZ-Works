package ezworks.project.users.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Roles")
@Getter
@Setter
// Para separar las tablas de los roles específicos
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name; // Nombre del rol;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person; // Relación con la entidad Person
}
