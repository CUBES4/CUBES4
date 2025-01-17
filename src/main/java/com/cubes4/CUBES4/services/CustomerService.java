package com.cubes4.CUBES4.services;

import com.cubes4.CUBES4.dto.CustomerDTO;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
public interface CustomerService {
    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);

    List<CustomerDTO> getCustomersByLastName(String lastName);

    List<CustomerDTO> getCustomersByFirstName(String firstName);

    List<CustomerDTO> getClientByPhoneNumber(String phoneNumber);

    List<CustomerDTO> getCustomersByEmail(String email);

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(Long id, CustomerDTO updatedCustomerDTO);

    void deleteCustomer(Long id);

    List<String> getAllCustomerNames();

    Long getCustomerIdByFullName(String firstName, String lastName);

}
