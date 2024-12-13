package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.dto.ArticleDTO;
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
    public ResponseEntity<List<ArticleDTO>> getAllArticles() {
        return ResponseEntity.ok(articleService.getAllArticles());
    }

    @Operation(summary = "Récupérer un article par ID", description = "Renvoie les détails d'un article spécifique par son ID.")
    @ApiResponse(responseCode = "200", description = "Article trouvé")
    @ApiResponse(responseCode = "404", description = "Article introuvable")
    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    @Operation(summary = "Rechercher des articles par nom", description = "Récupère tous les articles avec le nom spécifié.")
    @ApiResponse(responseCode = "200", description = "Liste des articles retournée avec succès")
    @GetMapping("/recherche")
    public ResponseEntity<List<ArticleDTO>> getArticleByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(articleService.getArticleByName(name));
    }

    @Operation(summary = "Rechercher des articles par prix maximal", description = "Récupère tous les articles dont le prix unitaire est inférieur à la valeur spécifiée.")
    @ApiResponse(responseCode = "200", description = "Liste des articles retournée avec succès")
    @PostMapping("/recherche/prix-max")
    public ResponseEntity<List<ArticleDTO>> getArticleByMaxPrice(@RequestBody Map<String, Double> criteria) {
        double maxPrice = criteria.get("maxPrice");
        return ResponseEntity.ok(articleService.getArticleByPriceLessThan(maxPrice));
    }

    @Operation(summary = "Rechercher des articles par stock minimum", description = "Récupère tous les articles dont le stock est supérieur ou égal à la valeur spécifiée.")
    @ApiResponse(responseCode = "200", description = "Liste des articles retournée avec succès")
    @PostMapping("/recherche/stock-min")
    public ResponseEntity<List<ArticleDTO>> getArticlesByStockMin(@RequestBody Map<String, Integer> criteria) {
        Integer minStock = criteria.get("minStock");
        return ResponseEntity.ok(articleService.getArticleByStockGreaterThan(minStock));
    }

    @Operation(summary = "Rechercher des articles par stock maximal",
            description = "Récupère tous les articles dont le stock est inférieur ou égal à la valeur spécifiée.")
    @ApiResponse(responseCode = "200", description = "Liste des articles retournée avec succès")
    @PostMapping("/recherche/stock-max")
    public ResponseEntity<List<ArticleDTO>> getArticlesByStockMax(@RequestBody Map<String, Integer> criteria) {
        Integer maxStock = criteria.get("maxStock");
        return ResponseEntity.ok(articleService.getArticleByStockLessThan(maxStock));
    }

    @Operation(summary = "Rechercher des articles par plage de stock", description = "Récupère tous les articles dont le stock est compris entre deux valeurs spécifiées.")
    @ApiResponse(responseCode = "200", description = "Liste des articles retournée avec succès")
    @PostMapping("/recherche/stock-range")
    public ResponseEntity<List<ArticleDTO>> getArticlesByStockRange(@RequestBody Map<String, Integer> criteria) {
        Integer stockRangeMin = criteria.get("stockMin");
        Integer stockRangeMax = criteria.get("stockMax");
        return ResponseEntity.ok(articleService.getArticleByStockBetween(stockRangeMin, stockRangeMax));
    }

    @Operation(summary = "Créer un nouvel article", description = "Ajoute un nouvel article dans la base de données.")
    @ApiResponse(responseCode = "201", description = "Article créé avec succès")
    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@RequestBody ArticleDTO articleDTO) {
        ArticleDTO savedArticle = articleService.createArticle(articleDTO);
        return ResponseEntity.ok(savedArticle);
    }

    @Operation(summary = "Mettre à jour un article", description = "Modifie les détails d'un article existant.")
    @ApiResponse(responseCode = "200", description = "Article mis à jour avec succès")
    @ApiResponse(responseCode = "404", description = "Article introuvable")
    @PutMapping("/{id}")
    public ResponseEntity<ArticleDTO> updateArticle(@PathVariable Long id, @RequestBody ArticleDTO updatedArticleDTO) {
        ArticleDTO savedArticle = articleService.updateArticle(id, updatedArticleDTO);
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