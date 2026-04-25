# Produits — Projet de cours Spring Boot

Ce dépôt contient un petit projet Spring Boot servant de support de cours. Il montre une base simple avec une entité JPA, un repository Spring Data JPA et une application Spring Boot prête à être enrichie.

## Objectif du projet

L’objectif est de manipuler les bases de Spring Boot autour de la persistance des données :

- démarrer une application Spring Boot
- créer une entité JPA (`Produit`)
- accéder à la base de données via un repository Spring Data JPA
- configurer une application avec MySQL
- écrire un test de contexte Spring

## Technologies utilisées

- Java 17
- Spring Boot 4.x
- Spring Data JPA
- MySQL
- Maven Wrapper (`mvnw`)

## Prérequis

Avant de lancer le projet, il faut avoir :

- Java 17 installé
- MySQL démarré localement
- une base accessible avec les identifiants définis dans `src/main/resources/application.properties`

Configuration actuelle :

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mydb?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=
spring.datasource.password=
server.port=8081
server.servlet.context-path=/produits
```

## Lancement du projet

Depuis la racine du projet :

```bash
./mvnw spring-boot:run
```

Sous macOS, si le fichier n’est pas exécutable :

```bash
chmod +x mvnw
./mvnw spring-boot:run
```

## Exécution des tests

Pour lancer les tests :

```bash
./mvnw test
```

## Structure du projet

```text
src/main/java/org/msn/produits/
├── ProduitsApplication.java      # Point d’entrée Spring Boot
├── entities/
│   └── Produit.java              # Entité JPA
└── repos/
    └── ProduitRepository.java    # Repository Spring Data JPA

src/test/java/org/msn/produits/
└── ProduitsApplicationTests.java  # Test de chargement du contexte
```

## Fichiers importants

### `ProduitsApplication.java`

Classe principale annotée avec `@SpringBootApplication`. Elle lance l’application Spring Boot.

### `Produit.java`

Entité JPA annotée avec `@Entity`.
Elle contient :

- `idProduit`
- `nomProduit`
- `prixProduit`
- `dateCreation`

### `ProduitRepository.java`

Interface Spring Data JPA qui hérite de `JpaRepository<Produit, Long>`.
Elle fournit les opérations CRUD de base.

### `ProduitsApplicationTests.java`

Test simple avec `@SpringBootTest` pour vérifier que le contexte Spring démarre correctement.

## Ce qu’il reste à faire dans le cours

Le projet est encore minimal. Les prochaines étapes possibles sont :

- ajouter un contrôleur REST
- exposer des endpoints pour gérer les produits
- créer une couche service
- ajouter des validations
- documenter les routes avec des exemples de requêtes

## Remarque

À ce stade, le projet contient la couche modèle + persistance, mais pas encore d’API exposée publiquement.

