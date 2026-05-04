package org.msn.produits.restcontrollers;

import lombok.AllArgsConstructor;
import org.msn.produits.entities.Produit;
import org.msn.produits.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// permet de définir une classe comme un contrôleur REST pour gérer les requêtes HTTP et retourner des réponses au format JSON ou XML
@RequestMapping("/api") // définit le chemin de base pour toutes les requêtes gérées par ce contrôleur, ici "/api"
@CrossOrigin("*")
// permet de gérer les requêtes cross-origin, c'est-à-dire les requêtes provenant de domaines différents, en autorisant toutes les origines ("*")
@AllArgsConstructor
// génère un constructeur avec tous les arguments pour les champs de la classe, ce qui facilite l'injection de dépendances
public class ProduitRESTController {

    //@Autowired
    private final ProduitService produitService; // déclare une dépendance à un service de gestion des produits, qui sera injecté automatiquement par Spring  via le constructeur généré par @AllArgsConstructor

    //@RequestMapping(method = RequestMethod.GET) // indique que cette méthode doit être appelée pour les requêtes HTTP GET, et le chemin de la requête est défini par l'annotation @RequestMapping au niveau de la classe
    @GetMapping("/all")
    // indique que cette méthode doit être appelée pour les requêtes HTTP GET
    public List<Produit> getAllProduits() {
        return produitService.getAllProduits();
    }

    //    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @GetMapping("/{id}")
    // indique que cette méthode doit être appelée pour les requêtes HTTP GET avec un chemin contenant un paramètre d'identification (id) du produit
    public Produit getProduitById(@PathVariable("id") Long id) {
        return produitService.getProduit(id);
    }


    @PostMapping // indique que cette méthode doit être appelée pour les requêtes HTTP POST
    public Produit createProduit(@RequestBody Produit produit) {
        return produitService.saveProduit(produit);
    }

    @PutMapping // indique que cette méthode doit être appelée pour les requêtes HTTP PUT
    public Produit updateProduit(@RequestBody Produit produit) {
        return produitService.updateProduit(produit);
    }

    @DeleteMapping("/{id}") // indique que cette méthode doit être appelée pour les requêtes HTTP DELETE
    public void deleteProduit(@PathVariable("id"
) Long id) {
        produitService.deleteProduitById(id);
    }

//    @RequestMapping(value = "/prodscat/{idCat}", method = RequestMethod.GET)
    @GetMapping("/prodscat/{idCat}")
    public List<Produit> getProduitsByCategorieId(@PathVariable("idCat") Long idCat) {
        return produitService.findByCategorieIdCat(idCat);
    }
    @RequestMapping(value="/prodsByName/{nom}",method = RequestMethod.GET)
//    @GetMapping("/prodsByName/{nom}")
    public List<Produit> findByNomProduitContains(@PathVariable("nom") String nom) {
        return produitService.findByNomProduitContains(nom);
    }

}
