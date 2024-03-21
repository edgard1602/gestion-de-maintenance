package com.inventaire.Inventaire_Actifs.controllers;

import com.inventaire.Inventaire_Actifs.model.Actif;
import com.inventaire.Inventaire_Actifs.services.Actif_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/actif")
public class Actif_controller {

    @Autowired
    private Actif_Service actifService;

    @PostMapping
    public ResponseEntity<Actif> createArticle(@RequestBody Actif actif) {
        Actif createdActif = actifService.createActif(actif);
        return new ResponseEntity<>(createdActif, HttpStatus.CREATED);
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
    public ResponseEntity<Void> deleteActif(@PathVariable Long id) {
        boolean deleted = actifService.deleteActif(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
