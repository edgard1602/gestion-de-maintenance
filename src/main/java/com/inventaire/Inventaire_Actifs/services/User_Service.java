package com.inventaire.Inventaire_Actifs.services;


import com.inventaire.Inventaire_Actifs.model.Token;
import com.inventaire.Inventaire_Actifs.model.UserDTO;
import com.inventaire.Inventaire_Actifs.repositories.TokenRepository;
import com.inventaire.Inventaire_Actifs.repositories.User_Repository;
import com.inventaire.Inventaire_Actifs.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class User_Service {

    @Autowired
    private User_Repository UserRepository;
    @Autowired
    private final TokenRepository tokenRepository;

    public User_Service(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }


    public UserDTO createUser(UserDTO userDTO) {
    User user = convertToEntity(userDTO);
    User savedUser = UserRepository.save(user);
    return mapUserToDTO(savedUser);
}


    public UserDTO getUserById(Integer id) {
        User user = UserRepository.findById(id).orElse(null);
        return user != null ? mapUserToDTO(user) : null;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = UserRepository.findAll();
        return users.stream()
                .map(this::mapUserToDTO)
                .collect(Collectors.toList());
    }
//methode pour convertir user en UserDTO
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




    public UserDTO updateUser(Integer id, UserDTO userDTO) {
        User existingUser = UserRepository.findById(id).orElse(null);
        if (existingUser != null) {
            User updatedUser = convertToEntity(userDTO);
            updatedUser.setId(existingUser.getId()); // Assurez-vous que l'ID reste le même
            User savedUser = UserRepository.save(updatedUser);
            return mapUserToDTO(savedUser);
        }
        return null;
    }




//    public User updateUser(Integer userId, UserDTO updatedUserDTO, List<Token> updatedTokens) {
//        User existingUser = UserRepository.findById(userId)
//                .orElseThrow(() -> new EntityNotFoundException("User not found"));
//
//        // Mettre à jour les attributs de l'utilisateur avec les nouvelles valeurs provenant du DTO
//        existingUser.setFirstName(updatedUserDTO.getFirstName());
//        existingUser.setLastName(updatedUserDTO.getLastName());
//        existingUser.setUsername(updatedUserDTO.getUsername());
//        existingUser.setEmail(updatedUserDTO.getEmail());
//        existingUser.setPassword(updatedUserDTO.getPassword());
//        existingUser.setRole(updatedUserDTO.getRole());
//
//        // Mettre à jour les tokens associés à l'utilisateur
//        if (updatedTokens != null) {
//            List<Token> existingTokens = existingUser.getTokens();
//
//            for (Token updatedToken : updatedTokens) {
//                Optional<Token> existingTokenOpt = existingTokens.stream()
//                        .filter(token -> token.getId().equals(updatedToken.getId()))
//                        .findFirst();
//                if (existingTokenOpt.isPresent()) {
//                    // Mettre à jour les attributs du token avec les nouvelles valeurs
//                    Token existingToken = existingTokenOpt.get();
//                    existingToken.setToken(updatedToken.getToken());
//                    existingToken.setLoggedOut(updatedToken.isLoggedOut());
//
//                    // Mettre à jour le lien avec l'utilisateur si nécessaire
//                    existingToken.setUser(existingUser);
//
//                    // Enregistrer les modifications du token
//                    tokenRepository.save(existingToken);
//                }
//            }
//        }
//
//        // Sauvegarder l'utilisateur mis à jour
//        return UserRepository.save(existingUser);
//    }

    public boolean deleteUser(Integer id) {
        Optional<User> existingUser = UserRepository.findById(id);
        if (existingUser.isPresent()) {
            UserRepository.delete(existingUser.get());
            return true;
        }
        return false;
    }
}
