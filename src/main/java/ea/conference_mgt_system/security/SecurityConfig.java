package ea.conference_mgt_system.security;

import ea.conference_mgt_system.customer.CustomerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private CustomerDetailsService customerDetailsService;

    public SecurityConfig(CustomerDetailsService customerDetailsService) {
        this.customerDetailsService = customerDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf->csrf.disable())
//                .authorizeHttpRequests(authorizerreauth -> authorizerreauth
//                                .requestMatchers("/api/conferences", "/api/conferences/{id}", "api/conferences/workshop/{workshopId}/location/{location}")
//                                .permitAll()
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizer -> authorizer
                        .requestMatchers(
                        "/api/conferences",
                        "/api/conferences/{id}",
                        "/api/conferences/workshop/{workshopId}/location/{location}"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
