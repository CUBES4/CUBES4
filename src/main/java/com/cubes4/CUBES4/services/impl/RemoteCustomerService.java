package com.cubes4.CUBES4.services.impl;

import com.cubes4.CUBES4.dto.CustomerDTO;
import com.cubes4.CUBES4.exceptions.ResourceNotFoundException;
import com.cubes4.CUBES4.mapper.CustomerMapper;
import com.cubes4.CUBES4.models.Customer;
import com.cubes4.CUBES4.repositories.CustomerRepository;
import com.cubes4.CUBES4.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RemoteCustomerService implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        return customerMapper.toDTO(customer);
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        customer = customerRepository.saveAndFlush(customer);
        return customerMapper.toDTO(customer);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO updatedCustomerDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        customerMapper.updateEntity(customer, updatedCustomerDTO);
        customer = customerRepository.saveAndFlush(customer);
        return customerMapper.toDTO(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        customerRepository.delete(customer);
    }
    @Override
    public List<String> getAllCustomerNames() {
        return customerRepository.findAll().stream()
                .map(customer -> customer.getFirstName() + " " + customer.getLastName())
                .collect(Collectors.toList());
    }
    public Long getCustomerIdByFullName(String firstName, String lastName) {
        return customerRepository.findByFirstNameAndLastName(firstName, lastName)
                .map(Customer::getId)
                .orElseThrow(() -> new ResourceNotFoundException("Client introuvable : " + firstName + " " + lastName));
    }

}
