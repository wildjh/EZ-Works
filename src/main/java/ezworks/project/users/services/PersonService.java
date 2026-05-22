package ezworks.project.users.services;

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

    public Person createPerson(Person person) {
        // Verificar si el correo electrónico ya existe
        if (personRepository.existsByEmail(person.getEmail())) {
            throw new IllegalArgumentException("El correo electrónico ya está registrado.");
        }

        // Encriptar la contraseña antes de guardarla
        String encodedPassword = passwordEncoder.encode(person.getPassword());
        person.setPassword(encodedPassword);

        return personRepository.save(person);
    }

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
}
