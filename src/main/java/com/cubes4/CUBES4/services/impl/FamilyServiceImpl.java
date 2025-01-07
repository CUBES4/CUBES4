package com.cubes4.CUBES4.services.impl;

import com.cubes4.CUBES4.dto.FamilyDTO;
import com.cubes4.CUBES4.exceptions.ResourceNotFoundException;
import com.cubes4.CUBES4.mapper.FamilyMapper;
import com.cubes4.CUBES4.models.Family;
import com.cubes4.CUBES4.repositories.FamilyRepository;
import com.cubes4.CUBES4.services.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private FamilyMapper familyMapper;

    @Override
    public List<FamilyDTO> getAllFamilies() {
        return familyRepository.findAll().stream()
                .map(familyMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FamilyDTO getFamilyById(Long id) {
        Family family = familyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Family not found with id: " + id));
        return familyMapper.toDTO(family);
    }

    @Override
    public FamilyDTO createFamily(FamilyDTO familyDTO) {
        if (familyRepository.existsByName(familyDTO.getName())) {
            throw new IllegalArgumentException("Une famille avec ce nom existe déjà.");
        }
        Family family = new Family();
        family.setName(familyDTO.getName());
        Family savedFamily = familyRepository.save(family);
        return familyMapper.toDTO(savedFamily);
    }



    @Override
    public FamilyDTO updateFamily(Long id, FamilyDTO familyDTO) {
        Family family = familyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Family not found with id: " + id));
        family.setName(familyDTO.getName());
        return familyMapper.toDTO(familyRepository.save(family));
    }

    @Override
    public void deleteFamily(Long id) {
        familyRepository.deleteById(id);
    }
}
