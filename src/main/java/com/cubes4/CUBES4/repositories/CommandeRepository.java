package com.cubes4.CUBES4.repositories;

import com.cubes4.CUBES4.models.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
public interface CommandeRepository extends JpaRepository<Commande, Long> {
}
