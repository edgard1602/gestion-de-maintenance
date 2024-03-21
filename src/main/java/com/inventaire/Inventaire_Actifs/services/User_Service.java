package com.inventaire.Inventaire_Actifs.services;


import com.inventaire.Inventaire_Actifs.repositories.User_Repository;
import com.inventaire.Inventaire_Actifs.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User_Service {

    @Autowired
    private User_Repository UserRepository;

    public User createUser(User user) {
        return UserRepository.save(user);
    }

    public User getUserById(Long id) {
        return UserRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        return UserRepository.findAll();
    }

    public User updateUser(Long id, User user) {
        User existingUser = UserRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            return UserRepository.save(existingUser);
        }
        return null;
    }

    public boolean deleteUser(Long id) {
        User existingUser = UserRepository.findById(id).orElse(null);
        if (existingUser != null) {
            UserRepository.delete(existingUser);
            return true;
        }
        return false;
    }
}
