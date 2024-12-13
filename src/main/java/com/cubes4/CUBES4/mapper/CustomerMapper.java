package com.cubes4.CUBES4.mapper;

import com.cubes4.CUBES4.dto.CustomerDTO;
import com.cubes4.CUBES4.models.Customer;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Mapper pour convertir entre Customer et CustomerDTO.
 */
@Component
public class CustomerMapper {

    public CustomerDTO toDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setLastName(customer.getLastName());
        dto.setFirstName(customer.getFirstName());
        dto.setEmail(customer.getEmail());
        dto.setAddress(customer.getAddress());
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setOrders(customer.getOrders().stream().map(OrderDTO::getId).collect(Collectors.toList()));
        return dto;
    }

    public Customer toEntity(CustomerDTO dto, Customer existingCustomer) {
        Customer customer = existingCustomer != null ? existingCustomer : new Customer();
        customer.setLastName(dto.getLastName());
        customer.setFirstName(dto.getFirstName());
        customer.setEmail(dto.getEmail());
        customer.setAddress(dto.getAddress());
        customer.setPhoneNumber(dto.getPhoneNumber());
        // Les commandes ne sont pas directement mappées ici pour éviter les conflits de gestion.
        return customer;
    }
}
