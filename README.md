# Produits - Support de cours Spring Boot

Projet fil rouge pour apprendre Spring Boot avec une architecture simple :

- entites JPA (`Produit`, `Categorie`)
- repository Spring Data JPA + Spring Data REST
- couche service
- controleur REST
- tests d'integration sur le repository

Le but est de manipuler le CRUD, les requetes derivees/JPQL, les relations JPA et l'exposition REST.

## 1) Stack technique

- Java 21
- Spring Boot `4.0.6`
- Spring Data JPA
- Spring Data REST
- MySQL
- Maven Wrapper (`mvnw`)

## 2) Configuration actuelle

Le fichier `src/main/resources/application.properties` contient :

```properties
spring.application.name=produits
spring.datasource.url=jdbc:mysql://localhost:3306/mydb?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=user
spring.datasource.password=password
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
server.servlet.context-path=/produits
server.port=8081
```

Donc l'application demarre sur : `http://localhost:8081/produits`.

## 3) Lancer le projet

Depuis la racine du projet :

```bash
./mvnw spring-boot:run
```

Si `mvnw` n'est pas executable (macOS/Linux) :

```bash
chmod +x mvnw
./mvnw spring-boot:run
```

## 4) Lancer les tests

```bash
./mvnw test
```

Pour un test specifique (ex: `testFindByNomPrix`) :

```bash
./mvnw -Dtest=ProduitsApplicationTests#testFindByNomPrix test
```

## 5) Architecture du projet

```text
src/main/java/org/msn/produits/
|- ProduitsApplication.java
|- entities/
|  |- Produit.java
|  |- Categorie.java
|  `- ProduitProjection.java
|- repos/
|  `- ProduitRepository.java
|- service/
|  |- ProduitService.java
|  `- ProduitServiceImpl.java
`- restcontrollers/
   `- ProduitRESTController.java

src/test/java/org/msn/produits/
`- ProduitsApplicationTests.java
```

## 6) Modele de donnees

### Entite `Produit`

- `idProduit` (PK, auto increment)
- `nomProduit`
- `prixProduit`
- `dateCreation`
- relation `@ManyToOne` vers `Categorie`

### Entite `Categorie`

- `idCat` (PK, auto increment)
- `nomCat`
- `descriptionCat`
- relation `@OneToMany(mappedBy = "categorie")` vers la liste de `Produit`

## 7) Repository et requetes

Le repository `ProduitRepository` expose :

- CRUD via `JpaRepository<Produit, Long>`
- recherches derivees :
  - `findByNomProduit(String nom)`
  - `findByNomProduitContains(String nom)`
  - `findByCategorieIdCat(Long id)`
  - `findByOrderByNomProduitAsc()`
- requetes JPQL :
  - `findByNomPrix(String nom, Double prix)`
  - `findByCategorie(Categorie categorie)`
  - `trierProduitsNomsPrix()`

## 8) Endpoints REST disponibles

Le projet propose 2 styles d'API.

### A. Controleur REST personnalise (`ProduitRESTController`)

Base : `/produits/api`

- `GET /produits/api` : liste des produits
- `GET /produits/api/{id}` : detail d'un produit
- `POST /produits/api` : creation
- `PUT /produits/api` : mise a jour
- `DELETE /produits/api/{id}` : suppression
- `GET /produits/api/prodscat/{idCat}` : produits d'une categorie

### B. Spring Data REST (`@RepositoryRestResource(path = "rest")`)

Base : `/produits/rest`

- collection des produits exposee automatiquement
- recherches via `/produits/rest/search`
- exemple : `/produits/rest/search/findByNomProduit?nom=MacBook`

## 9) Projection REST

La projection `ProduitProjection` (`@Projection(name = "nomProd", types = Produit.class)`) permet de ne retourner que `nomProduit`.

Exemple d'utilisation :

```text
GET /produits/rest?projection=nomProd
```

## 10) Jeu de donnees SQL (cours)

Le schema observe pour `produit` est :

- `id_produit`
- `date_creation`
- `nom_produit`
- `prix_produit`
- `categorie_id_cat`

Exemple d'insertion categories + produits :

```sql
INSERT INTO categorie (nom_cat, description_cat) VALUES
('Informatique', 'Ordinateurs et accessoires'),
('Telephonie', 'Smartphones et accessoires');

INSERT INTO produit (date_creation, nom_produit, prix_produit, categorie_id_cat) VALUES
(NOW(), 'MacBook Air M3', 1299.99, (SELECT id_cat FROM categorie WHERE nom_cat = 'Informatique' LIMIT 1)),
(NOW(), 'iPhone 15', 1199.99, (SELECT id_cat FROM categorie WHERE nom_cat = 'Telephonie' LIMIT 1));
```

## 11) Tests presents

`ProduitsApplicationTests` contient des tests d'integration repository :

- creation / lecture / modification / suppression
- recherche par nom
- recherche par nom + prix (JPQL)
- recherche par categorie
- tri

## 12) Depannage rapide

### Erreur `ExceptionInInitializerError` + `TypeTag :: UNKNOWN`

Souvent lie a un probleme d'outillage Java (JDK/annotation processors) plutot qu'a la requete JPQL elle-meme.

Points a verifier :

1. JDK du projet = Java 21 (IDE + Maven)
2. reimport Maven dans l'IDE
3. `./mvnw clean test`
4. verifier les plugins et dependances incompatibles

### Warning Mockito agent dynamique

Le projet configure `maven-surefire-plugin` dans `pom.xml` avec :

- `-javaagent:...mockito-core...jar`
- `-Xshare:off`

Ce parametrage evite les warnings de self-attach sur les JDK recents.

## 13) Pistes d'amelioration

- ajouter des DTO et mapping
- ajouter validation (`@NotNull`, `@Size`, etc.)
- gerer les exceptions globalement (`@ControllerAdvice`)
- ajouter des tests slices (`@DataJpaTest`, tests web)
- documenter l'API (OpenAPI/Swagger)

