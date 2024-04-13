package com.inventaire.Inventaire_Actifs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.inventaire.Inventaire_Actifs.model.User;

import java.util.Optional;

public interface User_Repository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
