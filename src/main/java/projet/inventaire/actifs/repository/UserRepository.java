package projet.inventaire.actifs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.inventaire.actifs.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}

