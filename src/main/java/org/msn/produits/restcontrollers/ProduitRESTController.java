package org.msn.produits.restcontrollers;

import org.msn.produits.entities.Produit;
import org.msn.produits.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // permet de définir une classe comme un contrôleur REST pour gérer les requêtes HTTP et retourner des réponses au format JSON ou XML
@RequestMapping("/api") // définit le chemin de base pour toutes les requêtes gérées par ce contrôleur, ici "/api"
@CrossOrigin("*") // permet de gérer les requêtes cross-origin, c'est-à-dire les requêtes provenant de domaines différents, en autorisant toutes les origines ("*")
public class ProduitRESTController {

    @Autowired
    ProduitService produitService;

    //@RequestMapping(method = RequestMethod.GET) // indique que cette méthode doit être appelée pour les requêtes HTTP GET, et le chemin de la requête est défini par l'annotation @RequestMapping au niveau de la classe
    @GetMapping // indique que cette méthode doit être appelée pour les requêtes HTTP GET
    List<Produit> getAllProduits() {;
        return produitService.getAllProduits();
    }
}
