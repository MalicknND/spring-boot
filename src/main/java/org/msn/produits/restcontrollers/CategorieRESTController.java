package org.msn.produits.restcontrollers;

import org.msn.produits.entities.Categorie;
import org.msn.produits.repos.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cat")
@CrossOrigin("*")
public class CategorieRESTController {

    @Autowired
    CategorieRepository categorieRepository;

    @GetMapping()
    public List<Categorie> getAllCategorie() {
        return categorieRepository.findAll();
    }

    @GetMapping("/{id}")
    public Categorie getCategorieById(@PathVariable("id") Long id) {
        return categorieRepository.findById(id).get();
    }
}
