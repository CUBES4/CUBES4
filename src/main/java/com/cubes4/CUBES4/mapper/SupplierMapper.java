package com.cubes4.CUBES4.mapper;

import com.cubes4.CUBES4.dto.SupplierDTO;
import com.cubes4.CUBES4.models.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@Mapper(componentModel = "spring")
public interface SupplierMapper {
    SupplierMapper INSTANCE = Mappers.getMapper(SupplierMapper.class);

    SupplierDTO supplierToSupplierDto(Supplier article);

    Supplier supplierDtoToSupplier(SupplierDTO dto, @MappingTarget Supplier existingSupplier);
}
