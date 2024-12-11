package com.cubes4.CUBES4.repositories;

import com.cubes4.CUBES4.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
public interface ClientRepository extends JpaRepository<Client, Long> {
}
