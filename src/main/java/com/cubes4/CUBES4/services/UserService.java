package com.cubes4.CUBES4.services;

import com.cubes4.CUBES4.dto.UserDTO;

public interface UserService {
    UserDTO validateUser(String username, String password);
    void createUser(UserDTO userDTO, String rawPassword);

    boolean authenticate(String username, String password);
}
