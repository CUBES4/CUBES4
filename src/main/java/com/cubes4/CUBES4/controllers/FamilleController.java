package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.models.Famille;
import com.cubes4.CUBES4.repositories.FamilleRepository;
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
@RequestMapping("/api/familles")
public class FamilleController {

    @Autowired
    private FamilleRepository familleRepository;

    @GetMapping
    public List<Famille> getAllFamilles() {
        return familleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Famille> getFamilleById(@PathVariable Long id) {
        return familleRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Famille> createFamille(@RequestBody Famille famille) {
        Famille savedFamille = familleRepository.save(famille);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFamille);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Famille> updateFamille(@PathVariable Long id, @RequestBody Famille updatedFamille) {
        return familleRepository.findById(id)
                .map(famille -> {
                    famille.setNom(updatedFamille.getNom());
                    famille.setArticles(updatedFamille.getArticles());
                    Famille saved = familleRepository.save(famille);
                    return ResponseEntity.ok(saved);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFamille(@PathVariable Long id) {
        return familleRepository.findById(id)
                .map(famille -> {
                    familleRepository.delete(famille);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
