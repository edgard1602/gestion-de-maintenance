package com.inventaire.Inventaire_Actifs.services;


import com.inventaire.Inventaire_Actifs.model.UserDTO;
import com.inventaire.Inventaire_Actifs.repositories.User_Repository;
import com.inventaire.Inventaire_Actifs.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class User_Service {

    @Autowired
    private User_Repository UserRepository;

//    public User createUser(User user) {
//        return UserRepository.save(user);
//    }
public UserDTO createUser(UserDTO userDTO) {
    User user = convertToEntity(userDTO);
    User savedUser = UserRepository.save(user);
    return mapUserToDTO(savedUser);
}
//    public User getUserById(Long id) {
//        return UserRepository.findById(id).orElse(null);
//    }

//    public List<User> getAllUsers() {
//        return UserRepository.findAll();
//    }

    public UserDTO getUserById(Long id) {
        User user = UserRepository.findById(id).orElse(null);
        return user != null ? mapUserToDTO(user) : null;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = UserRepository.findAll();
        return users.stream()
                .map(this::mapUserToDTO)
                .collect(Collectors.toList());
    }

    private UserDTO mapUserToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        return userDTO;
    }


    // Méthode pour convertir UserDTO en User
    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        // Vous pouvez ajouter d'autres attributs selon vos besoins
        return user;
    }


//    public User updateUser(Long id, User user) {
//        User existingUser = UserRepository.findById(id).orElse(null);
//        if (existingUser != null) {
//            existingUser.setUsername(user.getUsername());
//            existingUser.setEmail(user.getEmail());
//            return UserRepository.save(existingUser);
//        }
//        return null;
//    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = UserRepository.findById(id).orElse(null);
        if (existingUser != null) {
            User updatedUser = convertToEntity(userDTO);
            updatedUser.setId(existingUser.getId()); // Assurez-vous que l'ID reste le même
            User savedUser = UserRepository.save(updatedUser);
            return mapUserToDTO(savedUser);
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
