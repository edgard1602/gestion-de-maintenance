package projet.inventaire.actifs.repository;

import projet.inventaire.actifs.model.Actif;


import org.springframework.data.jpa.repository.JpaRepository;
import projet.inventaire.actifs.model.Actif;

public interface ActifRepository extends JpaRepository<Actif, Long> {
}
