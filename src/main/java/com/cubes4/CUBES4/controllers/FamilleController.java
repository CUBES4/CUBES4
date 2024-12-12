package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.models.Famille;
import com.cubes4.CUBES4.services.FamilleService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private FamilleService familleService;

    @GetMapping
    public ResponseEntity<List<Famille>> getAllFamilles() {
        List<Famille> familles = familleService.getAllFamilles();
        return ResponseEntity.ok(familles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Famille> getFamilleById(@PathVariable Long id) {
        Famille famille = familleService.getFamilleById(id);
        return ResponseEntity.ok(famille);
    }

    @PostMapping
    public ResponseEntity<Famille> createFamille(@RequestBody Famille famille) {
        Famille savedFamille = familleService.createFamille(famille);
        return ResponseEntity.ok(savedFamille);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Famille> updateFamille(@PathVariable Long id, @RequestBody Famille updatedFamille) {
        Famille savedFamille = familleService.updateFamille(id, updatedFamille);
        return ResponseEntity.ok(savedFamille);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFamille(@PathVariable Long id) {
        familleService.deleteFamille(id);
        return ResponseEntity.noContent().build();
    }
}
