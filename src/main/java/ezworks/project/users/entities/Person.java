package ezworks.project.users.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "persons")
@Getter
@Setter
@NoArgsConstructor
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String lastName;
    
    @Column(unique = true)
    private String email;
    private String password;
    private String number; 

    // Relacion de muchos a muchos con Role
    @ManyToMany(mappedBy =  "persons", cascade = CascadeType.ALL)
    private List<Role> roles;
}
