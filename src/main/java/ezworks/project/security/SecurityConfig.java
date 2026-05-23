package ezworks.project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http
                .csrf(AbstractHttpConfigurer::disable)              // En APIs REST suele deshabilitarse si no se usa cookie-based session
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()              // Permite todas las rutas sin autenticación
                )
                .httpBasic(Customizer.withDefaults());     // Opcional, no obliga a autenticación
        return http.build();
    }

    // Bean para que PersonService y controladores que inyectan PasswordEncoder sigan funcionando
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
