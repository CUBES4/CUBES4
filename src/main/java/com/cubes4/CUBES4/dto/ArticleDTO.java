package com.cubes4.CUBES4.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
public class ArticleDTO {

    private Long id;

    @NotNull(message = "Le nom de l'article ne peut pas être nul")
    @Size(min = 1, max = 255, message = "Le nom de l'article doit être compris entre 1 et 255 caractères")
    private String name;


    @Size(max = 1000, message = "La description ne doit pas dépasser 1000 caractères")
    private String description;

    @Positive(message = "Le prix unitaire doit être positif")
    private double unitPrice;

    @Positive(message = "Le stock doit être positif")
    private Integer stock;

    @Positive(message = "Le stock minimum doit être positif")
    private Integer stockMin;
    
    private Long familyId;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStockMin() {
        return stockMin;
    }

    public void setStockMin(Integer stockMin) {
        this.stockMin = stockMin;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }
}
