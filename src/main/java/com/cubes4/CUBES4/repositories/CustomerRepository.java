package com.cubes4.CUBES4.repositories;

import com.cubes4.CUBES4.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByLastNameContainingIgnoreCase(String name);

    List<Customer> findByFirstNameContainingIgnoreCase(String firstName);

    List<Customer> findByEmailContainingIgnoreCase(String email);

    List<Customer> findByPhoneNumberContainingIgnoreCase(String phoneNumber);
    // Rechercher un client par son prénom et son nom
    Optional<Customer> findByFirstNameAndLastName(String firstName, String lastName);

    // Rechercher des clients par prénom ou nom partiel (si nécessaire)
    List<Customer> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);

}
