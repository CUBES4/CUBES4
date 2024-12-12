package com.cubes4.CUBES4.models;

import jakarta.persistence.*;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@Entity
@Table(name = "families")
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy = "family")
    private List<Article> articles;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nom) {
        this.name = nom;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
