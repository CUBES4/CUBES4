package com.cubes4.CUBES4.repositories;

import com.cubes4.CUBES4.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByNameContainingIgnoreCase(String nom);

    List<Article> findByUnitPriceLessThan(double unitPrice);

    List<Article> findByStockGreaterThanEqual(Integer stock);

    List<Article> findByStockLessThanEqual(Integer stock);

    List<Article> findByStockBetween(Integer stockMin, Integer stockMax);
}
