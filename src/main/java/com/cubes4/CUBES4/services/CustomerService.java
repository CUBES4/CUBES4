package com.cubes4.CUBES4.services;

import com.cubes4.CUBES4.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(Long id, CustomerDTO updatedCustomerDTO);

    void deleteCustomer(Long id);

    List<String> getAllCustomerNames();

    Long getCustomerIdByFullName(String firstName, String lastName);

}
