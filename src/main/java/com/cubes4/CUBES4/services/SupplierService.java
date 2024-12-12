package com.cubes4.CUBES4.services;

import com.cubes4.CUBES4.annotation.Loggable;
import com.cubes4.CUBES4.exceptions.ResourceNotFoundException;
import com.cubes4.CUBES4.models.Supplier;
import com.cubes4.CUBES4.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@Loggable
@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id:" + id));
    }

    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.saveAndFlush(supplier);
    }

    public Supplier updateSupplier(Long id, Supplier updatedSupplier) {
        return supplierRepository.findById(id)
                .map(supplier -> {
                    supplier.setName(updatedSupplier.getName());
                    supplier.setEmail(updatedSupplier.getEmail());
                    supplier.setPhoneNumber(updatedSupplier.getPhoneNumber());
                    supplier.setAddress(updatedSupplier.getAddress());
                    supplier.setArticles(updatedSupplier.getArticles());
                    return supplierRepository.saveAndFlush(supplier);
                }).orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id:" + id));
    }

    public void deleteSupplier(Long id) {
        Supplier supplier = getSupplierById(id);
        supplierRepository.delete(supplier);
    }
}
