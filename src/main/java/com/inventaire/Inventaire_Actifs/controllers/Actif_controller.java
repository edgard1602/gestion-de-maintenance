package com.inventaire.Inventaire_Actifs.controllers;

import com.inventaire.Inventaire_Actifs.model.Actif;
import com.inventaire.Inventaire_Actifs.model.Agence;
import com.inventaire.Inventaire_Actifs.services.Actif_Service;
import com.inventaire.Inventaire_Actifs.services.AgenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/actif")
public class Actif_controller {

    @Autowired
    private Actif_Service actifService;

    @Autowired
    private AgenceService agenceService;




    @PostMapping
    public ResponseEntity<Actif> createActifWithAgence(@RequestBody Actif actif, @RequestParam("agenceId") Long agenceId) {
        Actif createdActif = actifService.createActifWithAgence(actif, agenceId);
        if (createdActif != null) {
            return new ResponseEntity<>(createdActif, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actif> getActifById(@PathVariable Long id) {
        Actif actif = actifService.getActifById(id);
        if (actif != null) {
            return new ResponseEntity<>(actif, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Actif>> getAllArticles() {
        List<Actif> actif = actifService.getAllActifs();
        return new ResponseEntity<>(actif, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actif> updateArticle(@PathVariable Long id, @RequestBody Actif actif) {
        Actif updatedActif = actifService.updateActif(id, actif);
        if (updatedActif != null) {
            return new ResponseEntity<>(updatedActif, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteActif(@PathVariable Long id) {
        boolean deleted = actifService.deleteActif(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{actifId}/associerAgence/{agenceId}")
    public ResponseEntity<Actif> associerActifAAgence(@PathVariable Long actifId, @PathVariable Long agenceId) {
        Actif actif = actifService.associerActifAAgence(actifId, agenceId);
        if (actif != null) {
            return new ResponseEntity<>(actif, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
