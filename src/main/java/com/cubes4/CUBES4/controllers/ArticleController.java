package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.models.Article;
import com.cubes4.CUBES4.services.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Operation(summary = "Récupérer tous les articles", description = "Renvoie la liste de tous les articles disponibles.")
    @ApiResponse(responseCode = "200", description = "Liste des articles retournée avec succès")
    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();
        return ResponseEntity.ok(articles);
    }

    @Operation(summary = "Récupérer un article par ID", description = "Renvoie les détails d'un article spécifique par son ID.")
    @ApiResponse(responseCode = "200", description = "Article trouvé")
    @ApiResponse(responseCode = "404", description = "Article introuvable")
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        Article article = articleService.getArticleById(id);
        return ResponseEntity.ok(article);
    }

    @Operation(summary = "Rechercher des articles par nom", description = "Récupère tous les articles avec le nom spécifié.")
    @ApiResponse(responseCode = "200", description = "Liste des articles retournée avec succès")
    @GetMapping("/recherche")
    public ResponseEntity<List<Article>> getArticleByNom(@RequestParam("nom") String nom) {
        List<Article> articles = articleService.getArticleByNom(nom);
        return ResponseEntity.ok(articles);
    }

    @Operation(summary = "Rechercher des articles par prix maximal", description = "Récupère tous les articles dont le prix unitaire est inférieur à la valeur spécifiée.")
    @ApiResponse(responseCode = "200", description = "Liste des articles retournée avec succès")
    @PostMapping("/recherche/prix-max")
    public ResponseEntity<List<Article>> getArticleByPrixMax(@RequestBody Map<String, Double> criteria) {
        double prixMax = criteria.get("prixMax");
        List<Article> articles = articleService.getArticleByPrixLessThan(prixMax);
        return ResponseEntity.ok(articles);
    }

    @Operation(summary = "Rechercher des articles par stock minimum", description = "Récupère tous les articles dont le stock est supérieur ou égal à la valeur spécifiée.")
    @ApiResponse(responseCode = "200", description = "Liste des articles retournée avec succès")
    @PostMapping("/recherche/stock-min")
    public ResponseEntity<List<Article>> getArticlesByStockMin(@RequestBody Map<String, Integer> criteria) {
        Integer stockMin = criteria.get("stockMin");
        List<Article> articles = articleService.getArticleByStockGreaterThan(stockMin);
        return ResponseEntity.ok(articles);
    }

    @Operation(summary = "Rechercher des articles par stock maximal",
            description = "Récupère tous les articles dont le stock est inférieur ou égal à la valeur spécifiée.")
    @ApiResponse(responseCode = "200", description = "Liste des articles retournée avec succès")
    @PostMapping("/recherche/stock-max")
    public ResponseEntity<List<Article>> getArticlesByStockMax(@RequestBody Map<String, Integer> criteria) {
        Integer stockMax = criteria.get("stockMax");
        List<Article> articles = articleService.getArticleByStockLessThan(stockMax);
        return ResponseEntity.ok(articles);
    }

    @Operation(summary = "Rechercher des articles par plage de stock", description = "Récupère tous les articles dont le stock est compris entre deux valeurs spécifiées.")
    @ApiResponse(responseCode = "200", description = "Liste des articles retournée avec succès")
    @PostMapping("/recherche/stock-range")
    public ResponseEntity<List<Article>> getArticlesByStockRange(@RequestBody Map<String, Integer> criteria) {
        Integer stockRangeMin = criteria.get("stockMin");
        Integer stockRangeMax = criteria.get("stockMax");
        List<Article> articles = articleService.getArticleByStockBetween(stockRangeMin, stockRangeMax);
        return ResponseEntity.ok(articles);
    }

    @Operation(summary = "Créer un nouvel article", description = "Ajoute un nouvel article dans la base de données.")
    @ApiResponse(responseCode = "201", description = "Article créé avec succès")
    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        Article savedArticle = articleService.createArticle(article);
        return ResponseEntity.ok(savedArticle);
    }

    @Operation(summary = "Mettre à jour un article", description = "Modifie les détails d'un article existant.")
    @ApiResponse(responseCode = "200", description = "Article mis à jour avec succès")
    @ApiResponse(responseCode = "404", description = "Article introuvable")
    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article updatedArticle) {
        Article savedArticle = articleService.updateArticle(id, updatedArticle);
        return ResponseEntity.ok(savedArticle);
    }

    @Operation(summary = "Supprimer un article", description = "Supprime un article par ID.")
    @ApiResponse(responseCode = "204", description = "Article supprimé avec succès")
    @ApiResponse(responseCode = "404", description = "Article introuvable")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
}