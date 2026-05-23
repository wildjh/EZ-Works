package ezworks.project.job.entities;

import ezworks.project.users.entities.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "matchings")
@Getter
@Setter
@NoArgsConstructor
public class Matching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "matching_date")
    private LocalDateTime matchingDate;

    // Relación con el Empleo
    @ManyToOne
    @JoinColumn(name = "job_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Job job;

    // --- ¡CONEXIÓN REAL CON EL MÓDULO DE USUARIOS HECHA! ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id") // Mantenemos el mismo nombre de columna física en la BD
    // Evitamos que traiga datos sensibles o pesados del candidato al listar los matchings
    @JsonIgnoreProperties({"password", "role", "hibernateLazyInitializer", "handler"})
    private Person employee;

    // Helper para asignar la fecha automáticamente al crear el match
    @PrePersist
    protected void onCreate() {
        this.matchingDate = LocalDateTime.now();
    }
}