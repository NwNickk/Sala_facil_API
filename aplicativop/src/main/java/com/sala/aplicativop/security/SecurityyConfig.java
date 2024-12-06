package com.sala.aplicativop.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityyConfig {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/usuarios/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuarios/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/usuarios/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/usuarios").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/usuarios/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/usuarios/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/salas").permitAll()
                        .requestMatchers(HttpMethod.GET, "/salas/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/salas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/salas/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/salas/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/reservas").permitAll()
                        .requestMatchers(HttpMethod.GET, "/reservas/data/{dataReserva}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/reservas/{usuarioId}/usuarios").permitAll()
                        .requestMatchers(HttpMethod.GET, "/reservas/{salaId}/salas").permitAll()
                        .requestMatchers(HttpMethod.GET, "/reservas/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/reservas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/reservas/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/reservas/{id}").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.setContentType("application/json");
                            response.getWriter().write("{\"message\": \"Você não está autorizado a fazer isso\"}");
                        })
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
