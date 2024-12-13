package com.cubes4.CUBES4.services;

import com.cubes4.CUBES4.dto.ArticleDTO;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
public interface ArticleService {
    List<ArticleDTO> getAllArticles();

    ArticleDTO getArticleById(Long id);

    List<ArticleDTO> getArticleByName(String name);

    List<ArticleDTO> getArticleByPriceLessThan(double unitPrice);

    List<ArticleDTO> getArticleByStockGreaterThan(Integer stock);

    List<ArticleDTO> getArticleByStockLessThan(Integer stock);

    List<ArticleDTO> getArticleByStockBetween(Integer stockMin, Integer stockMax);

    ArticleDTO createArticle(ArticleDTO articleDTO);

    ArticleDTO updateArticle(Long id, ArticleDTO updatedArticleDTO);

    void deleteArticle(Long id);
}
