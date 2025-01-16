package com.cubes4.CUBES4.repositories;

import com.cubes4.CUBES4.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByLastNameContainingIgnoreCase(String name);

    List<Customer> findByFirstNameContainingIgnoreCase(String firstName);

    List<Customer> findByEmailContainingIgnoreCase(String email);

    List<Customer> findByPhoneNumberContainingIgnoreCase(String phoneNumber);

    Optional<Customer> findByFirstNameAndLastName(String firstName, String lastName);

    List<Customer> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);

    @Query("SELECT CONCAT(c.firstName, ' ', c.lastName) FROM Customer c WHERE c.id = :customerId")
    String findFullNameByCustomerId(@Param("customerId") Long customerId);
}
