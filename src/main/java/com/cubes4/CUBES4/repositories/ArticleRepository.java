package com.cubes4.CUBES4.repositories;

import com.cubes4.CUBES4.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
