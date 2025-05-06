package dev.jonkursani.restapigr2.configs;

import dev.jonkursani.restapigr2.entities.User;
import dev.jonkursani.restapigr2.repositories.UserRepository;
import dev.jonkursani.restapigr2.security.AppUserDetailsService;
import dev.jonkursani.restapigr2.security.JwtAuthenticationFilter;
import dev.jonkursani.restapigr2.services.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static dev.jonkursani.restapigr2.entities.Permission.*;
import static dev.jonkursani.restapigr2.entities.Role.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationService authenticationService) {
        return new JwtAuthenticationFilter(authenticationService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/departments/**").permitAll()

                        // management controller
                        // cfar roli duhet me pas me i'u qas ketij endpoint (hasRole, hasAnyRole)
                        // hasRole kur eshte vetem nje, hasAnyRole kur keni me shume se nje
                        .requestMatchers("api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name())

                        // cfar permission ka ni endpoint specifik (hasAuthority, hasAnyAuthority)
                        // hasAuthority kur eshte vetem nje, hasAnyAuthority kur keni me shume se nje
                        .requestMatchers(HttpMethod.GET, "api/v1/management/**")
                            .hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
                        .requestMatchers(HttpMethod.POST, "api/v1/management/**")
                            .hasAnyAuthority(ADMIN_WRITE.name(), MANAGER_WRITE.name())
                        .requestMatchers(HttpMethod.PUT, "api/v1/management/**")
                            .hasAnyAuthority(ADMIN_WRITE.name(), MANAGER_WRITE.name())
                        .requestMatchers(HttpMethod.DELETE, "api/v1/management/**")
                            .hasAnyAuthority(ADMIN_WRITE.name(), MANAGER_WRITE.name())

                        // admin controller
//                        .requestMatchers("api/v1/admin/**").hasRole(ADMIN.name())
//                        // endpoint specifik
//                        .requestMatchers(HttpMethod.GET, "api/v1/admin/**").hasAuthority(ADMIN_READ.name())
//                        .requestMatchers(HttpMethod.POST, "api/v1/admin/**").hasAuthority(ADMIN_WRITE.name())
//                        .requestMatchers(HttpMethod.PUT, "api/v1/admin/**").hasAuthority(ADMIN_WRITE.name())
//                        .requestMatchers(HttpMethod.DELETE, "api/v1/admin/**").hasAuthority(ADMIN_WRITE.name())

                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {}) // Enable CORS with the bean from CorsConfig
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // para se me shku ne filter shtoje jwtAuthenticationFilter
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // JWT per ate e perdorim auth Stateless edhe e bejme disable CSRF

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        AppUserDetailsService appUserDetailsService = new AppUserDetailsService(userRepository);

        String email = "user@test.com";
        userRepository.findByEmail(email)
                .orElseGet(() -> {
                    var user = new User()
                            .builder()
                            .name("User")
                            .email(email)
                            .role(USER)
                            .password(passwordEncoder().encode("password"))
                            .build();

                    return userRepository.save(user);
                });

        return appUserDetailsService;
    }
}