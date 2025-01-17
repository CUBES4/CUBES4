package com.cubes4.CUBES4.repositories;

import com.cubes4.CUBES4.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Optional<Supplier> findBySupplierName(String supplierName);

    @Query("SELECT s.supplierName FROM Supplier s WHERE s.id = :supplierId")
    String findNameBySupplierId(@Param("supplierId") Long supplierId);
}
