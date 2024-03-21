package com.inventaire.Inventaire_Actifs.repositories;

import com.inventaire.Inventaire_Actifs.model.Actif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface Actif_Repository extends JpaRepository<Actif, Long> {
}
