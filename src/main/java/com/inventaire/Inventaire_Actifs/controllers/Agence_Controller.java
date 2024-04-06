package com.inventaire.Inventaire_Actifs.controllers;

import com.inventaire.Inventaire_Actifs.model.Agence;
import com.inventaire.Inventaire_Actifs.services.AgenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/agences")
public class Agence_Controller {
    @Autowired
    private AgenceService agenceService;

    @GetMapping
    public ResponseEntity<List<Agence>> getAllAgences() {
        List<Agence> agences = agenceService.getAllAgences();
        return new ResponseEntity<>(agences, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agence> getAgenceById(@PathVariable Long id) {
        Optional<Agence> agence = agenceService.getAgenceById(id);
        return agence.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Agence> createAgence(@RequestBody Agence agence) {
        Agence createdAgence = agenceService.createAgence(agence);
        return new ResponseEntity<>(createdAgence, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agence> updateAgence(@PathVariable Long id, @RequestBody Agence agence) {
        Agence updatedAgence = agenceService.updateAgence(id, agence);
        if (updatedAgence != null) {
            return new ResponseEntity<>(updatedAgence, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgence(@PathVariable Long id) {
        agenceService.deleteAgence(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
