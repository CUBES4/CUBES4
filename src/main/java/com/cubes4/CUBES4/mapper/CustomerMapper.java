package com.cubes4.CUBES4.mapper;

import com.cubes4.CUBES4.dto.CustomerDTO;
import com.cubes4.CUBES4.models.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * Mapper pour convertir entre Customer et CustomerDTO.
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDto(Customer customer);

    Customer customerDtoToCustomer(CustomerDTO dto, @MappingTarget Customer existingCustomer);
}
