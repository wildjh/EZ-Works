package ezworks.project.job.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "emparejamientos")
@Getter
@Setter
@NoArgsConstructor
public class Matching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "matching_date")
    private LocalDateTime matchingDate;
    // Relación directa con el Empleo (Este sí existe en nuestro paquete actual)
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    // --- CONEXIÓN CON EL MÓDULO DE USUARIOS ---

    // Por ahora usamos Integer porque la clase Empleado no existe aún.
    // Cuando creemos GestionUsuarios, esto pasará a ser un @ManyToOne
    @Column(name = "employee_id")
    private Integer employeeId;}