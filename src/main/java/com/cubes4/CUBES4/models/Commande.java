package com.cubes4.CUBES4.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@Entity
@Table(name = "commandes")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateCommande;
    private boolean isFournisseurCommande; // true if it's a supplier order, false if client order

    public enum CommandeStatus {
        PREPARATION,
        PRET,
        ENVOYE,
        LIVRE,
        RECU
    }

    private CommandeStatus statut; // e.g., "EN_COURS", "LIVRE", etc.

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    // If it's a fournisseur order, we may store a reference to the fournisseur
    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private List<ElementsCommande> elementsCommandes;

    public Long getId() {
        return id;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public boolean isFournisseurCommande() {
        return isFournisseurCommande;
    }

    public void setFournisseurCommande(boolean fournisseurCommande) {
        isFournisseurCommande = fournisseurCommande;
    }

    public CommandeStatus getStatut() {
        return statut;
    }

    public void setStatut(CommandeStatus statut) {
        this.statut = statut;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public List<ElementsCommande> getLigneCommandes() {
        return elementsCommandes;
    }

    public void setLigneCommandes(List<ElementsCommande> elementsCommandes) {
        this.elementsCommandes = elementsCommandes;
    }
}

