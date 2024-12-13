package com.cubes4.CUBES4.services;

import com.cubes4.CUBES4.dto.CustomerDTO;
import com.cubes4.CUBES4.exceptions.ResourceNotFoundException;
import com.cubes4.CUBES4.mapper.CustomerMapper;
import com.cubes4.CUBES4.models.Customer;
import com.cubes4.CUBES4.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service pour la gestion des clients.
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        return customerMapper.toDTO(customer);
    }

    public List<CustomerDTO> getCustomersByLastName(String lastName) {
        return customerRepository.findByLastNameContainingIgnoreCase(lastName).stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<CustomerDTO> getCustomersByFirstName(String firstName) {
        return customerRepository.findByFirstNameContainingIgnoreCase(firstName).stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<CustomerDTO> getCustomersByEmail(String email) {
        return customerRepository.findByEmailContainingIgnoreCase(email).stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<CustomerDTO> getClientByPhoneNumber(String phoneNumber) {
        return customerRepository.findByPhoneNumberContainingIgnoreCase(phoneNumber).stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO, null);
        customerRepository.saveAndFlush(customer);
        return customerMapper.toDTO(customer);
    }

    public CustomerDTO updateCustomer(Long id, CustomerDTO updatedCustomerDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        customer = customerMapper.toEntity(updatedCustomerDTO, customer);
        customerRepository.saveAndFlush(customer);
        return customerMapper.toDTO(customer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        customerRepository.delete(customer);
    }
}
