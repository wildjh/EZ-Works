package ezworks.project.job.services;

import org.springframework.stereotype.Service;

import ezworks.project.job.entities.Job;
import ezworks.project.job.repositories.JobRepository;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    private final JobRepository jobRepository;

    // Inyección de dependencias
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    // Listar todos los empleos
    public List<Job> buscarTodos() {
        return jobRepository.findAll();
    }

    // Guardar un empleo (Sirve tanto para crear como para actualizar)
    public Job guardar(Job job) {
        // devolver la entidad resultante del save (contiene id generado y cambios del proveedor JPA)
        return jobRepository.save(job);
    }

    // Buscar un empleo por su ID (Para ver los detalles)
    public Job buscarPorId(Integer id) {
        Optional<Job> opcional = jobRepository.findById(id);
        return opcional.orElse(null);
    }

    // Buscar empleos por una categoría específica (Usando el método personalizado que creamos)
    public List<Job> buscarPorCategoria(Integer idCategoria) {
        return jobRepository.findByCategoryId(idCategoria);
    }

    // Eliminar un empleo
    public void eliminar(Integer id) {
        jobRepository.deleteById(id);
    }
}