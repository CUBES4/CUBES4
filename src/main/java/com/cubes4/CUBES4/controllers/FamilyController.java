package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.dto.FamilyDTO;
import com.cubes4.CUBES4.services.impl.FamilyServiceImpl;
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
    private FamilyServiceImpl familyService;

    @GetMapping
    public ResponseEntity<List<FamilyDTO>> getAllFamily() {
        return ResponseEntity.ok(familyService.getAllFamily());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FamilyDTO> getFamilyById(@PathVariable Long id) {
        return ResponseEntity.ok(familyService.getFamilyById(id));
    }

    @PostMapping
    public ResponseEntity<FamilyDTO> createFamily(@RequestBody FamilyDTO family) {
        return ResponseEntity.ok(familyService.createFamily(family));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FamilyDTO> updateFamily(@PathVariable Long id, @RequestBody FamilyDTO updatedFamily) {
        return ResponseEntity.ok(familyService.updateFamily(id, updatedFamily));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFamily(@PathVariable Long id) {
        familyService.deleteFamily(id);
        return ResponseEntity.noContent().build();
    }
}
