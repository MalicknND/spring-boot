package org.msn.produits.entities;

import org.springframework.data.rest.core.config.Projection;

// cette interface définit une projection pour l'entité Produit, qui permet de ne récupérer que le nom du produit lors d'une requête REST
@Projection(name = "nomProd", types = Produit.class)
public interface ProduitProjection {
    public String getNomProduit();
}
