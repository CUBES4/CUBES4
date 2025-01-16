package com.cubes4.CUBES4.repositories;

import com.cubes4.CUBES4.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByNameContainingIgnoreCase(String nom);

    Optional<Article> findByName(String name);

    List<Article> findByUnitPriceLessThan(double unitPrice);

    List<Article> findByStockGreaterThanEqual(Integer stock);

    List<Article> findByStockLessThanEqual(Integer stock);

    List<Article> findByStockBetween(Integer stockMin, Integer stockMax);

    @Query("SELECT a FROM Article a WHERE a.family.id = :familyId")
    List<Article> findByFamilyId(@Param("familyId") Long familyId);

    @Query("SELECT a FROM Article a WHERE a.stock < a.stockMin")
    List<Article> findArticlesBelowStockMin();
}
