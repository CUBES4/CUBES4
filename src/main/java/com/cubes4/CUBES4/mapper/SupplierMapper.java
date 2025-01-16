package com.cubes4.CUBES4.mapper;

import com.cubes4.CUBES4.dto.SupplierDTO;
import com.cubes4.CUBES4.models.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {

    public SupplierDTO toDTO(Supplier supplier) {
        SupplierDTO dto = new SupplierDTO();
        dto.setId(supplier.getId());
        dto.setSupplierName(supplier.getSupplierName());
        dto.setEmail(supplier.getEmail());
        dto.setPhoneNumber(supplier.getPhoneNumber());
        dto.setAddress(supplier.getAddress());
        return dto;
    }

    public Supplier toEntity(SupplierDTO dto, Supplier existingSupplier) {
        Supplier supplier = (existingSupplier == null) ? new Supplier() : existingSupplier;
        supplier.setSupplierName(dto.getSupplierName());
        supplier.setEmail(dto.getEmail());
        supplier.setPhoneNumber(dto.getPhoneNumber());
        supplier.setAddress(dto.getAddress());
        return supplier;
    }
}
