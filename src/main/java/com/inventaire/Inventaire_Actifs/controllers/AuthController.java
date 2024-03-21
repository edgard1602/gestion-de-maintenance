package com.inventaire.Inventaire_Actifs.controllers;

import com.inventaire.Inventaire_Actifs.DTOs.AuthRequestDTO;
import com.inventaire.Inventaire_Actifs.DTOs.JwtResponseDTO;
import com.inventaire.Inventaire_Actifs.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.authentication.AuthenticationManager;

import com.inventaire.Inventaire_Actifs.DTOs.JwtResponseDTO.*;

import static com.inventaire.Inventaire_Actifs.DTOs.JwtResponseDTO.builder;


public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService JwtService;

    @PostMapping("/api/v1/login")
    public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if(authentication.isAuthenticated()){
            String token = JwtService.GenerateToken(authRequestDTO.getUsername());
            return builder().accessToken(token).build();
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }
}
