package com.cubes4.CUBES4.services.impl;

import com.cubes4.CUBES4.dto.UserDTO;
import com.cubes4.CUBES4.exceptions.ResourceNotFoundException;
import com.cubes4.CUBES4.mapper.UserMapper;
import com.cubes4.CUBES4.models.User;
import com.cubes4.CUBES4.repositories.UserRepository;
import com.cubes4.CUBES4.services.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Maël NOUVEL <br>
 * 01/2025
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO validateUser(String username, String password) {
        System.out.println("Tentative de connexion pour l'utilisateur : " + username);

        User user = userRepository.findByUsername(username);
        if (user == null) {
            System.out.println("Utilisateur non trouvé : " + username);
            throw new ResourceNotFoundException("Identifiants invalides.");
        }

        System.out.println("Utilisateur trouvé : " + user.getUsername());
        System.out.println("Mot de passe en base : " + user.getPassword());
        System.out.println("Mot de passe saisi : " + password);

        if (!BCrypt.checkpw(password, user.getPassword())) {
            System.out.println("Mot de passe incorrect pour : " + username);
            throw new ResourceNotFoundException("Identifiants invalides.");
        }

        System.out.println("Authentification réussie pour : " + username);
        return userMapper.toDTO(user);
    }




    @Override
    public void createUser(UserDTO userDTO, String rawPassword) {
        User user = userMapper.toEntity(userDTO);
        user.setPassword(BCrypt.hashpw(rawPassword, BCrypt.gensalt()));
        userRepository.save(user);
    }

    @Override
    public boolean authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }
        return BCrypt.checkpw(password, user.getPassword());
    }
}
