package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.models.Customer;
import com.cubes4.CUBES4.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@RestController
@RequestMapping("/api/clients")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Récupérer tous les clients", description = "Renvoie la liste complète des clients.")
    @ApiResponse(responseCode = "200", description = "Liste des clients retournée avec succès")
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @Operation(summary = "Récupérer un client par ID", description = "Renvoie les détails d'un client.")
    @ApiResponse(responseCode = "200", description = "Customer trouvé")
    @ApiResponse(responseCode = "404", description = "Customer introuvable")
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @Operation(summary = "Rechercher des clients par nom ou prenom", description = "Récupère tous les clients avec le nom ou prenom spécifié.")
    @ApiResponse(responseCode = "200", description = "Liste des clients retournée avec succès")
    @GetMapping("/recherche")
    public ResponseEntity<List<Customer>> getCustomersByFirstOrLastName(
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String firstName) {

        List<Customer> customers;

        if (lastName != null) {
            customers = customerService.getCustomersByLastName(lastName);
        } else if (firstName != null) {
            customers = customerService.getCustomersByFirstName(firstName);
        } else {
            customers = customerService.getAllCustomers();
        }

        return ResponseEntity.ok(customers);
    }

    @Operation(summary = "Rechercher des clients par email", description = "Récupère tous les clients avec l'email spécifié.")
    @ApiResponse(responseCode = "201", description = "Liste des clients retournée avec succès")
    @GetMapping("/recherche/email")
    public ResponseEntity<List<Customer>> getCustomersByEmail(String email) {
        List<Customer> customers = customerService.getCustomersByEmail(email);
        return ResponseEntity.ok(customers);
    }

    @Operation(summary = "Rechercher des clients par numéro de télephone", description = "Récupère tous les clients avec le numéro de téléphone spécifié.")
    @ApiResponse(responseCode = "201", description = "Liste des clients retournée avec succès")
    @GetMapping("/recherche/telephone")
    public ResponseEntity<List<Customer>> getCustomersByPhoneNumber(String phoneNumber) {
        List<Customer> customers = customerService.getClientByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(customers);
    }

    @Operation(summary = "Créer un nouveau customer", description = "Ajoute un nouveau customer dans la base de données.")
    @ApiResponse(responseCode = "201", description = "Customer créé avec succès")
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.createCustomer(customer);
        return ResponseEntity.ok(savedCustomer);
    }

    @Operation(summary = "Mettre à jour un client", description = "Modifie les informations d'un client existant.")
    @ApiResponse(responseCode = "200", description = "Customer mis à jour avec succès")
    @ApiResponse(responseCode = "404", description = "Customer introuvable")
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        Customer savedCustomer = customerService.updateCustomer(id, updatedCustomer);
        return ResponseEntity.ok(savedCustomer);
    }

    @Operation(summary = "Supprimer un client", description = "Supprime un client existant par ID.")
    @ApiResponse(responseCode = "204", description = "Customer supprimé avec succès")
    @ApiResponse(responseCode = "404", description = "Customer introuvable")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

}
