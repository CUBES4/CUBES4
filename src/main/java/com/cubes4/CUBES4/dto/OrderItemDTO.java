package com.cubes4.CUBES4.dto;

import lombok.*;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItemDTO {
    private Long id;

    private Integer quantity;

    private OrderDTO order;

    private ArticleDTO article;
}
