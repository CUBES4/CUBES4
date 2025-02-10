package com.cubes4.CUBES4.mapper;

import com.cubes4.CUBES4.dto.ArticleDTO;
import com.cubes4.CUBES4.models.Article;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    ArticleDTO articleToArticleDto(Article article);

    Article articleDtoToArticle(ArticleDTO dto, @MappingTarget Article existingArticle);
}
