package com.inventaire.Inventaire_Actifs.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Actif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private String numeroSerie;
    private Date dateArrivee;
    private boolean estNeuf;
    private String personneReception;

    // Constructeurs, getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Date getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public boolean isEstNeuf() {
        return estNeuf;
    }

    public void setEstNeuf(boolean estNeuf) {
        this.estNeuf = estNeuf;
    }

    public String getPersonneReception() {
        return personneReception;
    }

    public void setPersonneReception(String personneReception) {
        this.personneReception = personneReception;
    }
}

