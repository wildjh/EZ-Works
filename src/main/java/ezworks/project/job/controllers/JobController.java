package ezworks.project.job.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ezworks.project.job.entities.Job;
import ezworks.project.job.entities.JobStatus;
import ezworks.project.job.services.CategoryService;
import ezworks.project.job.services.JobService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/empleos") // Todas las URLs empezarán con /empleos
@CrossOrigin(origins = "*")
public class JobController {

    private final JobService jobService;
    private final CategoryService categoryService;

    // Inyección de dependencias (PDF 7)
    public JobController(JobService jobService, CategoryService categoryService) {
        this.jobService = jobService;
        this.categoryService = categoryService;
    }

    // 1. Mostrar el listado de empleos
    @GetMapping("/listado")
    public ResponseEntity<List<Job>> listarEmpleos() {
        List<Job> jobs = jobService.buscarTodos();
//        // Convierte el status Integer a JobStatus para la vista
//        for (Job job : jobs) {
//            if (job.getStatus() != null) {
//                JobStatus statusEnum = JobStatus.values()[job.getStatus() - 1]; // Asumiendo índices 0-based
//                job.setStatus(statusEnum.getValue()); // O crea un campo temporal en Job
//            }
//        }
//        model.addAttribute("empleos", jobs);
//        return ; // También corrige el nombre de la vista (ver abajo)
        return ResponseEntity.ok(jobs);
    }


    // 2. Mostrar el formulario para crear un nuevo empleo (PDF 6)
    @GetMapping("/crear")
    public ResponseEntity<Map<String, Object>> mostrarFormulario() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("empleoTemplate", new Job()); // plantilla vacía para pruebas
        payload.put("categorias", categoryService.buscarTodas());
        payload.put("estados", JobStatus.values());
        return ResponseEntity.ok(payload);

    }

    // 3. Recibir los datos del formulario y guardarlos en la Base de Datos (PDF 7)
    @PostMapping("/guardar")
    public ResponseEntity<Job> guardarEmpleo(@RequestBody Job job) {
//        jobService.guardar(job);
//        // Después de guardar, redirigimos al usuario a la tabla de listado
//        return "redirect:/empleos/listado";
        Job saved = jobService.guardar(job);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);

    }
}