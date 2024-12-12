package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.models.Supplier;
import com.cubes4.CUBES4.services.SupplierService;
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
@RequestMapping("/api/fournisseurs")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Operation(summary = "Récupérer tous les fournisseurs", description = "Renvoie la liste des fournisseurs disponibles.")
    @ApiResponse(responseCode = "200", description = "Liste des fournisseurs retournée avec succès")
    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    @Operation(summary = "Récupérer un fournisseur par ID", description = "Renvoie les détails d'un fournisseur existant par son ID.")
    @ApiResponse(responseCode = "200", description = "Supplier trouvé")
    @ApiResponse(responseCode = "404", description = "Supplier introuvable")
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
        Supplier supplier = supplierService.getSupplierById(id);
        return ResponseEntity.ok(supplier);
    }

    @Operation(summary = "Créer un nouveau supplier", description = "Ajoute un nouveau supplier dans la base de données.")
    @ApiResponse(responseCode = "201", description = "Article créé avec succès")
    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
        Supplier savedSupplier = supplierService.createSupplier(supplier);
        return ResponseEntity.ok(savedSupplier);
    }

    @Operation(summary = "Mettre à jour un fournisseur", description = "Modifie les informations d'un fournisseur existant.")
    @ApiResponse(responseCode = "200", description = "Supplier mis à jour avec succès")
    @ApiResponse(responseCode = "404", description = "Supplier introuvable")
    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable Long id, @RequestBody Supplier updatedSupplier) {
        Supplier savedSupplier = supplierService.updateSupplier(id, updatedSupplier);
        return ResponseEntity.ok(savedSupplier);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }

}
