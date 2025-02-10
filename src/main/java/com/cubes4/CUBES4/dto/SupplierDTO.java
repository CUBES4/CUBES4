package com.cubes4.CUBES4.dto;

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
public class SupplierDTO {
    private Long id;

    private String name;

    private String address;

    private String email;

    private String phoneNumber;

    private List<ArticleDTO> articles;
}
