package ezworks.project.users.services;

import ezworks.project.security.RegistrationRequest;
import ezworks.project.users.entities.Role;
import ezworks.project.users.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ezworks.project.users.entities.Person;

import ezworks.project.users.repositories.PersonRepository;

@Service
public class PersonService {
    @Autowired    
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public Person findByEmail(String email) {
        return personRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con el correo electrónico: " + email));
    }
    public Person modifyData(Integer id, Person datosNuevos) {
        Person Person = personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con el ID: " + id));
        // Mapear cambios...
        Person.setName(datosNuevos.getName());
        Person.setLastName(datosNuevos.getLastName());
        Person.setNumber(datosNuevos.getNumber());
        // Si se proporciona una nueva contraseña, encriptarla antes de guardarla
        if (datosNuevos.getPassword() != null && !datosNuevos.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(datosNuevos.getPassword());
            Person.setPassword(encodedPassword);
        }
        return personRepository.save(Person);
    }

    public Person createPersonFromRequest(RegistrationRequest req) {
        // Validaciones básicas
        if (req.getEmail() == null || req.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email es requerido");
        }
        if (req.getPassword() == null || req.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password es requerido");
        }

        if (personRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("El correo electrónico ya está registrado.");
        }

        Person person = new Person();
        person.setName(req.getName());
        person.setLastName(req.getLastName());
        person.setEmail(req.getEmail());
        person.setNumber(req.getNumber());
        // set password later (encoded)

        // Resolver role: preferir roleId, si no, roleName
        Role role;
        if (req.getRoleId() != null) {
            role = roleRepository.findById(req.getRoleId())
                    .orElseThrow(() -> new IllegalArgumentException("Role no encontrado para id: " + req.getRoleId()));
        } else if (req.getRoleName() != null && !req.getRoleName().isBlank()) {
            role = roleRepository.findByName(req.getRoleName())
                    .orElseThrow(() -> new IllegalArgumentException("Role no encontrado para name: " + req.getRoleName()));
        } else {
            // comportamiento por defecto: elegir EMPLOYEE (o lanzar error si prefieres exigir selección)
            role = roleRepository.findByName("EMPLOYEE")
                    .orElseGet(() -> {
                        Role r = new Role();
                        r.setName("EMPLOYEE");
                        return roleRepository.save(r);
                    });
        }
        person.setRole(role);

        // Encriptar contraseña
        String encodedPassword = passwordEncoder.encode(req.getPassword());
        person.setPassword(encodedPassword);

        return personRepository.save(person);
    }

}
