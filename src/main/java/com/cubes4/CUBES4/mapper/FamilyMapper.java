package com.cubes4.CUBES4.mapper;

import com.cubes4.CUBES4.dto.FamilyDTO;
import com.cubes4.CUBES4.models.Family;
import org.springframework.stereotype.Component;

@Component
public class FamilyMapper {

    public FamilyDTO toDTO(Family family) {
        FamilyDTO dto = new FamilyDTO();
        dto.setId(family.getId());
        dto.setName(family.getName());
        return dto;
    }

    public Family toEntity(FamilyDTO dto) {
        Family family = new Family();
        family.setId(dto.getId());
        family.setName(dto.getName());
        return family;
    }
}
