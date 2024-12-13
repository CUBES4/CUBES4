package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.models.Family;
import com.cubes4.CUBES4.services.FamilyService;
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
public class FamilyController {

    @Autowired
    private FamilyService familyService;

    @GetMapping
    public ResponseEntity<List<Family>> getAllFamily() {
        List<Family> family = familyService.getAllFamily();
        return ResponseEntity.ok(family);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Family> getFamilyById(@PathVariable Long id) {
        Family family = familyService.getFamilyById(id);
        return ResponseEntity.ok(family);
    }

    @PostMapping
    public ResponseEntity<Family> createFamily(@RequestBody Family family) {
        Family savedFamily = familyService.createFamily(family);
        return ResponseEntity.ok(savedFamily);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Family> updateFamily(@PathVariable Long id, @RequestBody Family updatedFamily) {
        Family savedFamily = familyService.updateFamily(id, updatedFamily);
        return ResponseEntity.ok(savedFamily);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFamily(@PathVariable Long id) {
        familyService.deleteFamily(id);
        return ResponseEntity.noContent().build();
    }
}
