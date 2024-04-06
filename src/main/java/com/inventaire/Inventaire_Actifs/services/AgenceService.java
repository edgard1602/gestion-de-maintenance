package com.inventaire.Inventaire_Actifs.services;

import com.inventaire.Inventaire_Actifs.model.Agence;
import com.inventaire.Inventaire_Actifs.repositories.Agence_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgenceService {
    @Autowired
    private Agence_Repository agenceRepository;

    public List<Agence> getAllAgences() {
        return agenceRepository.findAll();
    }

    public Optional<Agence> getAgenceById(Long id) {
        return agenceRepository.findById(id);
    }

    public Agence createAgence(Agence agence) {
        return agenceRepository.save(agence);
    }

    public void deleteAgence(Long id) {
        agenceRepository.deleteById(id);
    }

    public Agence updateAgence(Long id, Agence updatedAgence) {
        Optional<Agence> optionalAgence = agenceRepository.findById(id);
        if (optionalAgence.isPresent()) {
            updatedAgence.setId(id);
            return agenceRepository.save(updatedAgence);
        } else {
            return null;
        }
    }

}
