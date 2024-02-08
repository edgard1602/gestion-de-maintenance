package projet.inventaire.actifs.service;
import projet.inventaire.actifs.model.Actif;

import java.util.List;
public interface ActifService {
    Actif createActif(Actif actif);

    Actif getActifById(Long id);

    List<Actif> getAllActif();

    Actif updateActif(Long id, Actif actif);

    boolean deleteActif(Long id);
}






