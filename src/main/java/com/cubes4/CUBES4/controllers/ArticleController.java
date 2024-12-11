package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.models.Article;
import com.cubes4.CUBES4.repositories.ArticleRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Operation(summary = "Récupérer tous les articles", description = "Renvoie la liste de tous les articles disponibles.")
    @ApiResponse(responseCode = "200", description = "Liste des articles retournée avec succès")
    @GetMapping
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Operation(summary = "Récupérer un article par ID", description = "Renvoie les détails d'un article spécifique par son ID.")
    @ApiResponse(responseCode = "200", description = "Article trouvé")
    @ApiResponse(responseCode = "404", description = "Article introuvable")
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        return articleRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Créer un nouvel article", description = "Ajoute un nouvel article dans la base de données.")
    @ApiResponse(responseCode = "201", description = "Article créé avec succès")
    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        Article savedArticle = articleRepository.save(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    @Operation(summary = "Mettre à jour un article", description = "Modifie les détails d'un article existant.")
    @ApiResponse(responseCode = "200", description = "Article mis à jour avec succès")
    @ApiResponse(responseCode = "404", description = "Article introuvable")
    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article updatedArticle) {
        return articleRepository.findById(id)
                .map(article -> {
                    article.setNom(updatedArticle.getNom());
                    article.setDescription(updatedArticle.getDescription());
                    article.setPrixUnitaire(updatedArticle.getPrixUnitaire());
                    article.setStock(updatedArticle.getStock());
                    article.setStockMin(updatedArticle.getStockMin());
                    article.setFamille(updatedArticle.getFamille());
                    Article saved = articleRepository.save(article);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Supprimer un article", description = "Supprime un article par ID.")
    @ApiResponse(responseCode = "204", description = "Article supprimé avec succès")
    @ApiResponse(responseCode = "404", description = "Article introuvable")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteArticle(@PathVariable Long id) {
        return articleRepository.findById(id)
                .map(article -> {
                    articleRepository.delete(article);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}