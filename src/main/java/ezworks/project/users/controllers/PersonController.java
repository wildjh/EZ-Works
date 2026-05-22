package ezworks.project.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ezworks.project.users.entities.Person;
import ezworks.project.users.services.PersonService;

@RestController
@RequestMapping("/api/usuarios")
public class PersonController {

    @Autowired
    private PersonService PersonService;

    @PostMapping("/registro")
    public ResponseEntity<Person> registrar(@RequestBody Person Person) {
        return ResponseEntity.ok(PersonService.createPerson(Person));
    }

    @GetMapping("/buscar")
    public ResponseEntity<Person> buscarPorEmail(@RequestParam String email) {
        return ResponseEntity.ok(PersonService.findByEmail(email));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Person> actualizar(@PathVariable Integer id, @RequestBody Person Person) {
        return ResponseEntity.ok(PersonService.modifyData(id, Person));
    }
}