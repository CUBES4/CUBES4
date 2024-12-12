package com.cubes4.CUBES4.models;

import jakarta.persistence.*;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;
    private double unitPrice;
    private Integer stock;
    private Integer stockMin; // Minimum de stock pour un réapprovisionnement automatique si besoin

    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nom) {
        this.name = nom;
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

    public void setUnitPrice(double prixUnitaire) {
        this.unitPrice = prixUnitaire;
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

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }
}
