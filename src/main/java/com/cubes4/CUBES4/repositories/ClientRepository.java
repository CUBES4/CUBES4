package com.cubes4.CUBES4.repositories;

import com.cubes4.CUBES4.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByNomContainingIgnoreCase(String nom);

    List<Client> findByPrenomContainingIgnoreCase(String prenom);

    List<Client> findByEmailContainingIgnoreCase(String email);

    List<Client> findByTelephoneContainingIgnoreCase(String telephone);
}
