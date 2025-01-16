package com.cubes4.CUBES4.services.impl;

import com.cubes4.CUBES4.dto.SupplierDTO;
import com.cubes4.CUBES4.exceptions.ResourceNotFoundException;
import com.cubes4.CUBES4.mapper.SupplierMapper;
import com.cubes4.CUBES4.models.Supplier;
import com.cubes4.CUBES4.repositories.SupplierRepository;
import com.cubes4.CUBES4.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public List<SupplierDTO> getAllSuppliers() {
        return supplierRepository.findAll().stream()
                .map(supplierMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<String> getAllSupplierNames() {
        return supplierRepository.findAll()
                .stream()
                .map(Supplier::getSupplierName)
                .collect(Collectors.toList());
    }

    public Long getSupplierIdByName(String name) {
        return supplierRepository.findBySupplierName(name)
                .map(Supplier::getId)
                .orElse(null);
    }

    @Override
    public SupplierDTO getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id:" + id));
        return supplierMapper.toDTO(supplier);
    }

    @Override
    public SupplierDTO createSupplier(SupplierDTO supplierDTO) {
        Supplier supplier = supplierMapper.toEntity(supplierDTO, null);
        supplierRepository.saveAndFlush(supplier);
        return supplierMapper.toDTO(supplier);

    }

    @Override
    public SupplierDTO updateSupplier(Long id, SupplierDTO updatedSupplier) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id:" + id));
        supplier = supplierMapper.toEntity(updatedSupplier, supplier);
        supplierRepository.saveAndFlush(supplier);
        return supplierMapper.toDTO(supplier);
    }

    @Override
    public void deleteSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id:" + id));
        supplierRepository.delete(supplier);
    }
}
