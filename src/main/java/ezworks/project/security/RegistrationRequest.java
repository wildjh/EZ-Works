package ezworks.project.security;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String number;
    private Integer roleId;
    private String roleName;
}
