package com.cubes4.CUBES4.services;

import com.cubes4.CUBES4.dto.FamilyDTO;

import java.util.List;

public interface FamilyService {
    List<FamilyDTO> getAllFamilies();

    FamilyDTO getFamilyById(Long id);

    FamilyDTO createFamily(FamilyDTO familyDTO);

    FamilyDTO updateFamily(Long id, FamilyDTO updatedFamilyDTO);

    void deleteFamily(Long id);
}
