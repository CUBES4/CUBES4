package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.models.Fournisseur;
import com.cubes4.CUBES4.repositories.FournisseurRepository;
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

    @GetMapping
    public List<Fournisseur> getAllFournisseurs() {
        return fournisseurRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fournisseur> getFournisseurById(@PathVariable Long id) {
        return fournisseurRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Fournisseur> createFamille(@RequestBody Fournisseur fournisseur) {
        Fournisseur savedFamille = fournisseurRepository.save(fournisseur);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFamille);
    }

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
