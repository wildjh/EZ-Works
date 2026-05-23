package ezworks.project.users.controllers;

import ezworks.project.security.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ezworks.project.security.LoginRequest;
import ezworks.project.users.entities.Person;
import ezworks.project.users.services.PersonService;

@RestController
@RequestMapping("/api/usuarios")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody RegistrationRequest req) {
        try {
            Person created = personService.createPersonFromRequest(req);
            return ResponseEntity.ok(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno: " + e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Person person = personService.findByEmail(request.getEmail());

            if (passwordEncoder.matches(request.getPassword(), person.getPassword())) {
                return ResponseEntity.ok("Login exitoso");
            } else {
                return ResponseEntity.status(401).body("Credenciales inválidas");
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Usuario no encontrado");
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<Person> buscarPorEmail(@RequestParam String email) {
        return ResponseEntity.ok(personService.findByEmail(email));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Person> actualizar(@PathVariable Integer id, @RequestBody Person Person) {
        return ResponseEntity.ok(personService.modifyData(id, Person));
    }

}
