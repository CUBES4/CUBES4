package com.cubes4.CUBES4.services;

import com.cubes4.CUBES4.exceptions.ResourceNotFoundException;
import com.cubes4.CUBES4.models.Customer;
import com.cubes4.CUBES4.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
    }

    public List<Customer> getCustomersByLastName(String lastName) {
        return customerRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    public List<Customer> getCustomersByFirstName(String firstName) {
        return customerRepository.findByFirstNameContainingIgnoreCase(firstName);
    }

    public List<Customer> getCustomersByEmail(String email) {
        return customerRepository.findByEmailContainingIgnoreCase(email);
    }

    public List<Customer> getClientByPhoneNumber(String phoneNumber) {
        return customerRepository.findByPhoneNumberContainingIgnoreCase(phoneNumber);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setLastName(updatedCustomer.getLastName());
                    customer.setFirstName(updatedCustomer.getFirstName());
                    customer.setEmail(updatedCustomer.getEmail());
                    customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
                    customer.setAddress(updatedCustomer.getAddress());
                    customer.setOrders(updatedCustomer.getOrders());
                    return customerRepository.saveAndFlush(customer);
                }).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
    }

    public void deleteCustomer(Long id) {
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
    }
}
