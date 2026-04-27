package org.msn.produits.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCat;

    private String nomCat;
    private String descriptionCat;


    @OneToMany(mappedBy = "categorie")
    @JsonIgnore // pour éviter la sérialisation de la liste des produits lors de la conversion en JSON
    private List<Produit> produits;

}

