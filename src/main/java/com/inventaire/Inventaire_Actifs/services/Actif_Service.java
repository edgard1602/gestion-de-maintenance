package com.inventaire.Inventaire_Actifs.services;

import com.inventaire.Inventaire_Actifs.model.Actif;
import com.inventaire.Inventaire_Actifs.repositories.Actif_Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;


@Service
public class Actif_Service {
    @Autowired
    private Actif_Repository actifRepository;

    public List<Actif> getAllActifs() {
        return actifRepository.findAll();
    }


    public Actif getActifById(Long id) {
        return actifRepository.findById(id).orElse(null);
    }

    public Actif createActif(Actif actif) {
        return actifRepository.save(actif);
    }

    public Actif updateActif(Long id, Actif updatedActif) {
        Optional<Actif> optionalActif = actifRepository.findById(id);
        if (optionalActif.isPresent()) {
            updatedActif.setId(id);
            return actifRepository.save(updatedActif);
        } else {
            return null;
        }
    }

    public boolean deleteActif(Long id) {
        Optional<Actif> optionalActif = actifRepository.findById(id);
        if (optionalActif.isPresent()) {
            actifRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

