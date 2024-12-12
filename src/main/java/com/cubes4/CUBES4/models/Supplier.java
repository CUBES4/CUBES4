package com.cubes4.CUBES4.models;

import jakarta.persistence.*;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String address;
    private String email;
    private String phoneNumber;

    @ManyToMany
    @JoinTable(
            name = "supplier_article",
            joinColumns = @JoinColumn(name = "supplier_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id"))
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String adresse) {
        this.address = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String telephone) {
        this.phoneNumber = telephone;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
