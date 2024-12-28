package com.cubes4.CUBES4.mapper;

import com.cubes4.CUBES4.dto.ArticleDTO;
import com.cubes4.CUBES4.models.Article;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {

    public ArticleDTO toDTO(Article article) {
        ArticleDTO dto = new ArticleDTO();
        dto.setId(article.getId());
        dto.setName(article.getName());
        dto.setDescription(article.getDescription());
        dto.setUnitPrice(article.getUnitPrice());
        dto.setStock(article.getStock());
        dto.setStockMin(article.getStockMin());
        if (article.getFamily() != null) {
            dto.setFamilyId(article.getFamily().getId());
        }
        return dto;
    }

    public Article toEntity(ArticleDTO dto, Article existingArticle) {
        Article article = (existingArticle == null) ? new Article() : existingArticle;
        article.setName(dto.getName());
        article.setDescription(dto.getDescription());
        article.setUnitPrice(dto.getUnitPrice());
        article.setStock(dto.getStock());
        article.setStockMin(dto.getStockMin());
        return article;
    }
}
