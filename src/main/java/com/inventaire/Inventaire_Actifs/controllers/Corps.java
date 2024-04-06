package com.inventaire.Inventaire_Actifs.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Corps implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // permet à toutes les URL d'accepter les requêtes CORS
                .allowedOrigins("http://localhost:4200") // autoriser les requêtes provenant de http://localhost:4200
                .allowedMethods("GET", "POST", "PUT", "DELETE") // autoriser les méthodes HTTP
                .allowedHeaders("*"); // autoriser tous les en-têtes HTTP
    }
}
