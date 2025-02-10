package com.cubes4.CUBES4.mapper;

import com.cubes4.CUBES4.dto.OrderDTO;
import com.cubes4.CUBES4.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDTO articleToArticleDto(Order article);

    Order articleDtoToArticle(OrderDTO dto,@MappingTarget Order existingArticle);
}
