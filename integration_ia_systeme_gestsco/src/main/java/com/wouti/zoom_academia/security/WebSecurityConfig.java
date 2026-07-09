package com.wouti.zoom_academia.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    // Définition d'un bean PasswordEncoder pour encoder les mots de passe
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Désactive CSRF pour des tests, mais il est recommandé de l'activer en production
        http
            .csrf().disable()
            .authorizeHttpRequests(authorize -> authorize
                // Autorise toutes les requêtes pour le moment
                .anyRequest().permitAll()
            );

        return http.build();
    }
}
