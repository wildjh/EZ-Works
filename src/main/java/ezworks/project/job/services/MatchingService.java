package ezworks.project.job.services;

import org.springframework.stereotype.Service;

import ezworks.project.job.entities.Matching;
import ezworks.project.job.repositories.MatchingRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MatchingService {

    private final MatchingRepository matchingRepository;

    public MatchingService(MatchingRepository matchingRepository) {
        this.matchingRepository = matchingRepository;
    }

    // Guardar un nuevo emparejamiento
    public void guardar(Matching matching) {
        // Le asignamos la fecha y hora actual automáticamente si no la tiene
        if (matching.getMatchingDate() == null) {
            matching.setMatchingDate(LocalDateTime.now());
        }
        matchingRepository.save(matching);
    }

    // Buscar los candidatos/emparejamientos de un trabajo específico
    public List<Matching> buscarPorEmpleo(Integer jobId) {
        return matchingRepository.findByJobId(jobId);
    }
}