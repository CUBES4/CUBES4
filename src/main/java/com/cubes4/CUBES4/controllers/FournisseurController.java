package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.models.Fournisseur;
import com.cubes4.CUBES4.repositories.FournisseurRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@RestController
@RequestMapping("/api/fournisseurs")
public class FournisseurController {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @Operation(summary = "Récupérer tous les fournisseurs", description = "Renvoie la liste des fournisseurs disponibles.")
    @ApiResponse(responseCode = "200", description = "Liste des fournisseurs retournée avec succès")
    @GetMapping
    public List<Fournisseur> getAllFournisseurs() {
        return fournisseurRepository.findAll();
    }

    @Operation(summary = "Récupérer un fournisseur par ID", description = "Renvoie les détails d'un fournisseur existant par son ID.")
    @ApiResponse(responseCode = "200", description = "Fournisseur trouvé")
    @ApiResponse(responseCode = "404", description = "Fournisseur introuvable")
    @GetMapping("/{id}")
    public ResponseEntity<Fournisseur> getFournisseurById(@PathVariable Long id) {
        return fournisseurRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Créer un nouveau fournisseur", description = "Ajoute un nouveau fournisseur dans la base de données.")
    @ApiResponse(responseCode = "201", description = "Article créé avec succès")
    @PostMapping
    public ResponseEntity<Fournisseur> createFournisseur(@RequestBody Fournisseur fournisseur) {
        Fournisseur savedFamille = fournisseurRepository.save(fournisseur);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFamille);
    }

    @Operation(summary = "Mettre à jour un fournisseur", description = "Modifie les informations d'un fournisseur existant.")
    @ApiResponse(responseCode = "200", description = "Fournisseur mis à jour avec succès")
    @ApiResponse(responseCode = "404", description = "Fournisseur introuvable")
    @PutMapping("/{id}")
    public ResponseEntity<Fournisseur> updateFournisseur(@PathVariable Long id, @RequestBody Fournisseur updatedFournisseur) {
        return fournisseurRepository.findById(id)
                .map(fournisseur -> {
                    fournisseur.setNom(updatedFournisseur.getNom());
                    fournisseur.setEmail(updatedFournisseur.getEmail());
                    fournisseur.setTelephone(updatedFournisseur.getTelephone());
                    fournisseur.setAdresse(updatedFournisseur.getAdresse());
                    fournisseur.setArticles(updatedFournisseur.getArticles());
                    Fournisseur saved = fournisseurRepository.save(fournisseur);
                    return ResponseEntity.ok(saved);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFournisseur(@PathVariable Long id) {
        return fournisseurRepository.findById(id)
                .map(fournisseur -> {
                    fournisseurRepository.delete(fournisseur);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
