package com.cubes4.CUBES4.repositories;

import com.cubes4.CUBES4.models.Family;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRepository extends JpaRepository<Family, Long> {

        boolean existsByName(String name);

}
