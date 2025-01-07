package com.cubes4.CUBES4.models;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import jakarta.persistence.*;

@Entity
@Table(name = "families")
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public Family() {
        this.id = 0L; // Initialiser à zéro pour éviter les problèmes de null
        this.name = "";
    }

    public Family(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters et setters
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
}
