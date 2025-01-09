package com.cubes4.CUBES4.repositories;

import com.cubes4.CUBES4.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
