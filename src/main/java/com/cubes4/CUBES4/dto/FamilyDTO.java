package com.cubes4.CUBES4.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FamilyDTO {
    private long id;

    @NotNull(message = "Le nom ne peut pas être nul")
    @Size(min = 1, max = 100, message = "Le nom doit contenir entre 1 et 100 caractères")
    private String name;

    private List<ArticleDTO> articles;
}
