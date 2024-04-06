package com.inventaire.Inventaire_Actifs.services;

import com.inventaire.Inventaire_Actifs.model.Actif;
import com.inventaire.Inventaire_Actifs.model.Agence;
import com.inventaire.Inventaire_Actifs.repositories.Actif_Repository;
import com.inventaire.Inventaire_Actifs.repositories.Agence_Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;


@Service
public class Actif_Service {
    @Autowired
    private Actif_Repository actifRepository;
    @Autowired
    private Agence_Repository agenceRepository; // Assurez-vous d'injecter le repository de l'entité Agence


    public List<Actif> getAllActifs() {
        return actifRepository.findAll();
    }


    public Actif getActifById(Long id) {
        return actifRepository.findById(id).orElse(null);
    }

    public Actif createActif(Actif actif) {
        return actifRepository.save(actif);
    }



    public Actif createActifWithAgence(Actif actif, Long agenceId) {
        Optional<Agence> optionalAgence = agenceRepository.findById(agenceId);
        if (optionalAgence.isPresent()) {
            Agence agence = optionalAgence.get();
            actif.setAgence(agence);
            return actifRepository.save(actif);
        }
        return null;
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

    public Actif associerActifAAgence(Long actifId, Long agenceId) {
        Optional<Actif> optionalActif = actifRepository.findById(actifId);
        Optional<Agence> optionalAgence = agenceRepository.findById(agenceId);

        if (optionalActif.isPresent() && optionalAgence.isPresent()) {
            Actif actif = optionalActif.get();
            Agence agence = optionalAgence.get();

            actif.setAgence(agence); // Associer l'actif à l'agence
            return actifRepository.save(actif); // Sauvegarder les changements dans la base de données
        } else {
            return null; // Gérer le cas où l'actif ou l'agence n'existe pas
        }
    }














}

