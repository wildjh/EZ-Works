package ezworks.project.job.entities;

import ezworks.project.users.entities.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private LocalDate date;
    private Double salary;
    private Integer status;
    private Integer featured;
    private String image;
    private String details;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id") // Mantenemos el nombre de la columna en la BD
    // Ignoramos la contraseña, el rol y las listas internas para que el JSON quede impecable
    @JsonIgnoreProperties({"password", "role", "hibernateLazyInitializer", "handler"})
    private Person employer;
}