package com.cubes4.CUBES4.services;

import com.cubes4.CUBES4.dto.FamilyDTO;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
public interface FamilyService {

    List<FamilyDTO> getAllFamily();

    FamilyDTO getFamilyById(Long id);

    FamilyDTO createFamily(FamilyDTO familyDTO);

    FamilyDTO updateFamily(Long id, FamilyDTO updatedFamily);

    void deleteFamily(Long id);
}
