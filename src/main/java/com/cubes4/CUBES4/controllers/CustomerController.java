package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.dto.CustomerDTO;
import com.cubes4.CUBES4.services.impl.CustomerServiceImpl;
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
    private CustomerServiceImpl customerService;

    @Operation(summary = "Récupérer tous les clients", description = "Renvoie la liste complète des clients.")
    @ApiResponse(responseCode = "200", description = "Liste des clients retournée avec succès")
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @Operation(summary = "Récupérer un client par ID", description = "Renvoie les détails d'un client.")
    @ApiResponse(responseCode = "200", description = "Customer trouvé")
    @ApiResponse(responseCode = "404", description = "Customer introuvable")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @Operation(summary = "Rechercher des clients par nom ou prénom", description = "Récupère tous les clients avec le nom ou prénom spécifié.")
    @ApiResponse(responseCode = "200", description = "Liste des clients retournée avec succès")
    @GetMapping("/recherche")
    public ResponseEntity<List<CustomerDTO>> getCustomersByFirstOrLastName(
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String firstName) {

        List<CustomerDTO> customers;

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
    @ApiResponse(responseCode = "200", description = "Liste des clients retournée avec succès")
    @GetMapping("/recherche/email")
    public ResponseEntity<List<CustomerDTO>> getCustomersByEmail(@RequestParam String email) {
        return ResponseEntity.ok(customerService.getCustomersByEmail(email));
    }

    @Operation(summary = "Rechercher des clients par numéro de téléphone", description = "Récupère tous les clients avec le numéro de téléphone spécifié.")
    @ApiResponse(responseCode = "200", description = "Liste des clients retournée avec succès")
    @GetMapping("/recherche/telephone")
    public ResponseEntity<List<CustomerDTO>> getCustomersByPhoneNumber(@RequestParam String phoneNumber) {
        return ResponseEntity.ok(customerService.getClientByPhoneNumber(phoneNumber));
    }

    @Operation(summary = "Créer un nouveau client", description = "Ajoute un nouveau client dans la base de données.")
    @ApiResponse(responseCode = "201", description = "Customer créé avec succès")
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.createCustomer(customerDTO));
    }

    @Operation(summary = "Mettre à jour un client", description = "Modifie les informations d'un client existant.")
    @ApiResponse(responseCode = "200", description = "Customer mis à jour avec succès")
    @ApiResponse(responseCode = "404", description = "Customer introuvable")
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO updatedCustomerDTO) {
        return ResponseEntity.ok(customerService.updateCustomer(id, updatedCustomerDTO));
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
