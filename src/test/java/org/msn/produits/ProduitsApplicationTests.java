package org.msn.produits;

import org.junit.jupiter.api.Test;
import org.msn.produits.entities.Produit;
import org.msn.produits.repos.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProduitsApplicationTests {

    @Autowired
    private ProduitRepository produitRepository;

    @Test
    public void testCreateProduit() {
        Produit prod = new Produit("MacBook Air", 2000.0, new java.util.Date());
        produitRepository.save(prod);
    }

    @Test
    public void testFindProduit(){
        Produit prod = produitRepository.findById(1L).get();
        System.out.println(prod);
    }

    @Test
    public void testUpdateProduit(){
        Produit prod = produitRepository.findById(1L).get();
        prod.setNomProduit("MacBook Pro M3");
        produitRepository.save(prod);
    }

    @Test
    public void testDeleteProduit(){
        produitRepository.deleteById(1L);
    }

    @Test
    public void testFindAllProduits(){
        List<Produit> produits = produitRepository.findAll();
        for (Produit produit : produits) {
            System.out.println(produit);
        }
    }
}
