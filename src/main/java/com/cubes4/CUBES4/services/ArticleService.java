package com.cubes4.CUBES4.services;

import com.cubes4.CUBES4.models.Article;
import com.cubes4.CUBES4.repositories.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article not found with id: " + id));
    }

    public List<Article> getArticleByNom(String nom) {
        return articleRepository.findByNomContainingIgnoreCase(nom);
    }

    public List<Article> getArticleByPrixLessThan(double prixUnitaire) {
        return articleRepository.findByPrixUnitaireLessThan(prixUnitaire);
    }

    public List<Article> getArticleByStockGreaterThan(Integer stock) {
        return articleRepository.findByStockGreaterThanEqual(stock);
    }

    public List<Article> getArticleByStockLessThan(Integer stock) {
        return articleRepository.findByStockLessThanEqual(stock);
    }

    public List<Article> getArticleByStockBetween(Integer stockMin, Integer stockMax) {
        return articleRepository.findByStockBetween(stockMin, stockMax);
    }

    public Article createArticle(Article article) {
        return articleRepository.saveAndFlush(article);
    }

    public Article updateArticle(Long id, Article updatedArticle) {
        return articleRepository.findById(id)
                .map(article -> {
                    article.setNom(updatedArticle.getNom());
                    article.setDescription(updatedArticle.getDescription());
                    article.setPrixUnitaire(updatedArticle.getPrixUnitaire());
                    article.setStock(updatedArticle.getStock());
                    article.setStockMin(updatedArticle.getStockMin());
                    article.setFamille(updatedArticle.getFamille());
                    return articleRepository.saveAndFlush(article);
                }).orElseThrow(() -> new EntityNotFoundException("Article not found with id: " + id));
    }

    public void deleteArticle(Long id) {
        Article article = getArticleById(id);
        articleRepository.delete(article);
    }
}
