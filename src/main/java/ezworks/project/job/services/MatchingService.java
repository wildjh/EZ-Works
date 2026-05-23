package ezworks.project.job.services;

import ezworks.project.users.entities.Person;
import ezworks.project.users.repositories.PersonRepository;
import org.springframework.stereotype.Service;
import ezworks.project.job.entities.Matching;
import ezworks.project.job.entities.Job;
import ezworks.project.job.repositories.JobRepository;
import ezworks.project.job.repositories.MatchingRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MatchingService {

    private final MatchingRepository matchingRepository;
    private final JobRepository jobRepository;         // <- Añadido
    private final PersonRepository personaRepository;   // <- Añadido

    // Actualizamos el constructor para que Spring inyecte los 3 repositorios
    public MatchingService(MatchingRepository matchingRepository,
                           JobRepository jobRepository,
                           PersonRepository personaRepository) {
        this.matchingRepository = matchingRepository;
        this.jobRepository = jobRepository;
        this.personaRepository = personaRepository;
    }

    // Método para crear el match buscando las entidades reales
    public Matching crearMatchEfectivo(Integer jobId, Integer employeeId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("El empleo con ID " + jobId + " no existe."));

        Person empleado = personaRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("El usuario con ID " + employeeId + " no existe."));

        Matching matching = new Matching();
        matching.setJob(job);
        matching.setEmployee(empleado);
        matching.setMatchingDate(LocalDateTime.now());

        return matchingRepository.save(matching);
    }

    public List<Matching> buscarPorEmpleo(Integer jobId) {
        return matchingRepository.findByJobId(jobId);
    }
}