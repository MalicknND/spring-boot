package org.msn.produits;

import org.junit.jupiter.api.Test;
import org.msn.produits.entities.Categorie;
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
    public void testFindProduit() {
        Produit prod = produitRepository.findById(1L).get();
        System.out.println(prod);
    }

    @Test
    public void testUpdateProduit() {
        Produit prod = produitRepository.findById(1L).get();
        prod.setNomProduit("MacBook Pro M3");
        produitRepository.save(prod);
    }

    @Test
    public void testDeleteProduit() {
        produitRepository.deleteById(1L);
    }

    @Test
    public void testFindAllProduits() {
        List<Produit> produits = produitRepository.findAll();
        for (Produit produit : produits) {
            System.out.println(produit);
        }
    }

    @Test
    public void testFindProduitByNom() {
        List<Produit> prod = produitRepository.findByNomProduit("MacBook Pro");
        for (Produit produit : prod) {
            System.out.println(produit);
        }
    }

    @Test
    public void testFindProduitByNomContains() {
        List<Produit> prod = produitRepository.findByNomProduitContains("P");
        for (Produit produit : prod) {
            System.out.println(produit);
        }
    }

    @Test
    public void testFindByNomPrix() {
        List<Produit> prod = produitRepository.findByNomPrix("Macbook Pro", 1000.0);
        for (Produit p : prod) {
            System.out.println(p);
        }
    }

    @Test
    public void testFindByCategorie() {
        Categorie cat = new Categorie();
        cat.setIdCat(1L);
        List<Produit> prod = produitRepository.findByCategorie(cat);
        for (Produit p : prod) {
            System.out.println(p);
        }
    }

    @Test
    public void testFindByCategorieIdCat() {
        List<Produit> prod = produitRepository.findByCategorieIdCat(1L);
        for (Produit p : prod) {
            System.out.println(p);
        }
    }

}
