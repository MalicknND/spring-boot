package org.msn.produits.service;

import org.msn.produits.entities.Produit;

import java.util.List;

// cette interface définit les méthodes de service pour gérer les produits

public interface ProduitService {
    Produit saveProduit(Produit p);
    Produit updateProduit(Produit p);
    void deleteProduit(Produit p);
    void deleteProduitById(Long id);
    Produit getProduit(Long id);
    List<Produit> getAllProduits();
}
