package ezworks.project.job.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "empleo")
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

    @Column(name = "employer_id")
    private Integer employerId; // Temporal hasta que creemos la entidad Employer/User
}