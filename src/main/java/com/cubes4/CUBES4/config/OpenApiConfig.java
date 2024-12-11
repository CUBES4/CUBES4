package com.cubes4.CUBES4.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@Configuration
public class OpenApiConfig {

    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info().title("CUBES4 API")
                        .description("API pour les opérations CRUDs de base.")
                        .version("1.0.0")
                        .contact(new Contact().name("Maël NOUVEL").email("maelnouvel@duck.com").url("https://github.com/Mnov34"))
                        .extensions(Map.of("additionalContacts", List.of(
                                new Contact().name("Jules").url("https://github.com/CESIJules"),
                                new Contact().name("Mattieu").url("https://github.com/Nogardel"),
                                new Contact().name("Florian").url("https://github.com/Zedi13")))))
                .externalDocs(new ExternalDocumentation()
                        .description("Project GitHub")
                        .url("https://github.com/CUBES4/CUBES4"));
    }
}
