package com.cubes4.CUBES4.services;

import com.cubes4.CUBES4.dto.CustomerDTO;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);

    List<CustomerDTO> getCustomersByLastName(String lastName);

    List<CustomerDTO> getCustomersByFirstName(String firstName);

    List<CustomerDTO> getCustomersByEmail(String email);

    List<CustomerDTO> getClientByPhoneNumber(String phoneNumber);

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(Long id, CustomerDTO updatedCustomerDTO);

    void deleteCustomer(Long id);
}
