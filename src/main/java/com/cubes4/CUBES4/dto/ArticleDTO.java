package com.cubes4.CUBES4.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleDTO {

    private Long id;

    @NotNull(message = "Le nom de l'article ne peut pas être nul")
    @Size(min = 1, max = 255, message = "Le nom de l'article doit être compris entre 1 et 255 caractères")
    private String name;

    @Size(max = 1000, message = "La description ne doit pas dépasser 1000 caractères")
    private String description;

    @Positive(message = "Le prix unitaire doit être positif")
    private double unitPrice;

    @Positive(message = "Le stock doit être positif")
    private Integer stock;

    @Positive(message = "Le stock minimum doit être positif")
    private Integer stockMin;

    private Long familyId;
}
