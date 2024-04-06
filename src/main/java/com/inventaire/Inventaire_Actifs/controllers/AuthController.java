package com.inventaire.Inventaire_Actifs.controllers;

import com.inventaire.Inventaire_Actifs.DTOs.AuthRequestDTO;
import com.inventaire.Inventaire_Actifs.DTOs.JwtResponseDTO;
import com.inventaire.Inventaire_Actifs.services.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import static com.inventaire.Inventaire_Actifs.DTOs.JwtResponseDTO.builder;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService JwtService;

//    @PostMapping("/api/v1/login")
//    public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
//        if(authentication.isAuthenticated()){
//            return JwtResponseDTO.builder()
//                    .accessToken(JwtService.GenerateToken(authRequestDTO.getUsername()).build());
//        } else {
//            throw new UsernameNotFoundException("invalid user request..!!");
//        }
//    }

//    @PostMapping("/v1/login")
//    public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
//        if(authentication.isAuthenticated()){
//            JwtResponseDTO responseDTO = new JwtResponseDTO();
//            responseDTO.setAccessToken(JwtService.GenerateToken(authRequestDTO.getUsername()));
//            return responseDTO;
//        } else {
//            throw new UsernameNotFoundException("invalid user request..!!");
//        }
//    }


    @PostMapping("/login")
    public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if(authentication.isAuthenticated()){
            String token = JwtService.GenerateToken(authRequestDTO.getUsername());
            return builder().accessToken(token).build();
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        // Récupérer le token JWT du header Authorization
        String authHeader = request.getHeader("Authorization");
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }

        // Si le token est valide, l'invalider (ou marquer comme expiré)
        if (token != null) {
            // Invalider le token
            JwtService.invalidateToken(token);
            return ResponseEntity.ok("Logout successful");
        } else {
            return ResponseEntity.badRequest().body("Unable to logout");
        }
    }


}
