package com.cubes4.CUBES4.mapper;

import com.cubes4.CUBES4.dto.FamilyDTO;
import com.cubes4.CUBES4.models.Family;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@Mapper(componentModel = "spring")
public interface FamilyMapper {
    FamilyMapper INSTANCE = Mappers.getMapper(FamilyMapper.class);

    FamilyDTO familyToFamilyDto(Family family);

    Family familyDtoToFamily(FamilyDTO dto,@MappingTarget Family existingFamily);
}
