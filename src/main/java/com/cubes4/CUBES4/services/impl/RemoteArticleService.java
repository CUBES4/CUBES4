package com.cubes4.CUBES4.services.impl;

import com.cubes4.CUBES4.dto.ArticleDTO;
import com.cubes4.CUBES4.exceptions.ResourceNotFoundException;
import com.cubes4.CUBES4.mapper.ArticleMapper;
import com.cubes4.CUBES4.models.Article;
import com.cubes4.CUBES4.models.Family;
import com.cubes4.CUBES4.repositories.ArticleRepository;
import com.cubes4.CUBES4.repositories.FamilyRepository;
import com.cubes4.CUBES4.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RemoteArticleService implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private ArticleMapper articleMapper;


    public List<String> getAllArticleNames() {
        return articleRepository.findAll().stream()
                .map(Article::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDTO> getAllArticles() {
        return articleRepository.findAll().stream()
                .map(articleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ArticleDTO getArticleById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article introuvable avec id: " + id));
        return articleMapper.toDTO(article);
    }

    @Override
    public List<ArticleDTO> getArticleByName(String name) {
        return articleRepository.findByNameContainingIgnoreCase(name).stream()
                .map(articleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDTO> getArticlesByFamily(Long familyId) {
        return articleRepository.findByFamilyId(familyId).stream()
                .map(articleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDTO> getArticleByPriceLessThan(double unitPrice) {
        return articleRepository.findByUnitPriceLessThan(unitPrice).stream()
                .map(articleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDTO> getArticleByStockGreaterThan(Integer stock) {
        return articleRepository.findByStockGreaterThanEqual(stock).stream()
                .map(articleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDTO> getArticleByStockLessThan(Integer stock) {
        return articleRepository.findByStockLessThanEqual(stock).stream()
                .map(articleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDTO> getArticleByStockBetween(Integer stockMin, Integer stockMax) {
        return articleRepository.findByStockBetween(stockMin, stockMax).stream()
                .map(articleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ArticleDTO createArticle(ArticleDTO articleDTO) {
        Article article = articleMapper.toEntity(articleDTO, null);

        // Vérification et affectation de la famille
        if (articleDTO.getFamilyId() != null) {
            Family family = familyRepository.findById(articleDTO.getFamilyId())
                    .orElseThrow(() -> new ResourceNotFoundException("Famille introuvable avec id: " + articleDTO.getFamilyId()));
            article.setFamily(family);
        }

        // Enregistrement de l'article
        articleRepository.saveAndFlush(article);
        return articleMapper.toDTO(article);
    }

    @Override
    public ArticleDTO updateArticle(Long id, ArticleDTO updatedArticleDTO) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article introuvable avec id: " + id));

        // Mise à jour des champs de l'article
        article = articleMapper.toEntity(updatedArticleDTO, article);

        // Mise à jour de la famille si applicable
        if (updatedArticleDTO.getFamilyId() != null) {
            Family family = familyRepository.findById(updatedArticleDTO.getFamilyId())
                    .orElseThrow(() -> new ResourceNotFoundException("Famille introuvable avec id: " + updatedArticleDTO.getFamilyId()));
            article.setFamily(family);
        }

        // Enregistrement de l'article mis à jour
        articleRepository.saveAndFlush(article);
        return articleMapper.toDTO(article);
    }

    @Override
    public void deleteArticle(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article introuvable avec id: " + id));
        articleRepository.delete(article);
    }
}
