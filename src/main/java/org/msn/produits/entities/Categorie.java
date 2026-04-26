package org.msn.produits.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCat;

    private String nomCat;
    private Double descriptionCat;

    @OneToMany(mappedBy = "categorie")
    private List<Produit> produits;

    public Categorie() {
        super();
    }

    public Long getIdCat() {
        return idCat;
    }

    public void setIdCat(Long idCat) {
        this.idCat = idCat;
    }

    public String getNomCat() {
        return nomCat;
    }

    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }

    public Double getDescriptionCat() {
        return descriptionCat;
    }

    public void setDescriptionCat(Double descriptionCat) {
        this.descriptionCat = descriptionCat;
    }
}

