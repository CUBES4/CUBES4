package com.cubes4.CUBES4.repositories;

import com.cubes4.CUBES4.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository pour les fournisseurs
 **/
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
