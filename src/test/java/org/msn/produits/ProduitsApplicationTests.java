package org.msn.produits;

import org.junit.jupiter.api.Test;
import org.msn.produits.entities.Produit;
import org.msn.produits.repos.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProduitsApplicationTests {

    @Autowired
    private ProduitRepository produitRepository;

    @Test
    public void testCreateProduit() {
        Produit prod = new Produit("MacBook Pro", 2500.0, new java.util.Date());
        produitRepository.save(prod);
    }
}
