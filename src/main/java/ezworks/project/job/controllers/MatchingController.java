package ezworks.project.job.controllers;

import ezworks.project.job.entities.Matching;
import ezworks.project.job.services.MatchingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/matchings")
public class MatchingController {

    private final MatchingService matchingService;

    // Inyección por constructor (Práctica recomendada)
    public MatchingController(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearMatch(@RequestBody Map<String, Integer> payload) {
        try {
            Integer jobId = payload.get("jobId");
            Integer employeeId = payload.get("employeeId");

            // Ejecutamos la lógica del servicio
            Matching nuevoMatch = matchingService.crearMatchEfectivo(jobId, employeeId);
            return ResponseEntity.ok(nuevoMatch);
        } catch (Exception e) {
            // Si algún ID no existe o algo falla, nos avisa aquí
            return ResponseEntity.badRequest().body("Error al crear el match: " + e.getMessage());
        }
    }
}