package ezworks.project.job.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ezworks.project.job.entities.Job;
import ezworks.project.job.entities.JobStatus;
import ezworks.project.job.services.CategoryService;
import ezworks.project.job.services.JobService;

import java.util.List;

@Controller
@RequestMapping("/empleos") // Todas las URLs empezarán con /empleos
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
    public String listarEmpleos(Model model) {
        List<Job> jobs = jobService.buscarTodos();
        // Convierte el status Integer a JobStatus para la vista
        for (Job job : jobs) {
            if (job.getStatus() != null) {
                JobStatus statusEnum = JobStatus.values()[job.getStatus() - 1]; // Asumiendo índices 0-based
                job.setStatus(statusEnum.getValue()); // O crea un campo temporal en Job
            }
        }
        model.addAttribute("empleos", jobs);
        return "ListadoEmpleados"; // También corrige el nombre de la vista (ver abajo)
    }


    // 2. Mostrar el formulario para crear un nuevo empleo (PDF 6)
    @GetMapping("/crear")
    public String mostrarFormulario(Model model) {
        model.addAttribute("empleo", new Job());
        // ESTAS DOS LÍNEAS SON VITALES:
        model.addAttribute("categorias", categoryService.buscarTodas());
        model.addAttribute("estados", JobStatus.values());
        return "formEmpleo";
    }

    // 3. Recibir los datos del formulario y guardarlos en la Base de Datos (PDF 7)
    @PostMapping("/guardar")
    public String guardarEmpleo(Job job) {
        jobService.guardar(job);
        // Después de guardar, redirigimos al usuario a la tabla de listado
        return "redirect:/empleos/listado";
    }
}