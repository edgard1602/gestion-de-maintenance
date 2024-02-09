package projet.inventaire.actifs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.inventaire.actifs.model.Actif;
import projet.inventaire.actifs.repository.ActifRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ActifServiceImpl implements ActifService {

    @Autowired
    private ActifRepository actifRepository;

    @Override
    public Actif createActif(Actif actif) {
        return actifRepository.save(actif);
    }

    @Override
    public Actif getActifById(Long id) {
        Optional<Actif> actifOptional = actifRepository.findById(id);
        return actifOptional.orElse(null);
    }

    @Override
    public List<Actif> getAllActif() {
        return actifRepository.findAll();
    }

    @Override
    public Actif updateActif(Long id, Actif actif) {
        Optional<Actif> existingActifOptional = actifRepository.findById(id);
        if (existingActifOptional.isPresent()) {
            Actif existingActif = existingActifOptional.get();
            existingActif.setNom(actif.getNom());
            existingActif.setDescription(actif.getDescription());
            existingActif.setNumeroSerie(actif.getNumeroSerie());
            existingActif.setDateArrivee(actif.getDateArrivee());
            existingActif.setEstNeuf(actif.isEstNeuf());
            existingActif.setPersonneReception(actif.getPersonneReception());
            return actifRepository.save(existingActif);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteActif(Long id) {
        if (actifRepository.existsById(id)) {
            actifRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

