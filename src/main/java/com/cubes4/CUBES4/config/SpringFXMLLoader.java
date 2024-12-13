package com.cubes4.CUBES4.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@Component
public class SpringFXMLLoader {

    private final ApplicationContext applicationContext;

    public SpringFXMLLoader(ApplicationContext context) {
        this.applicationContext = context;
    }

    /**
     * Loads an FXML file and returns the root node.
     *
     * @param fxmlPath The path to the FXML file.
     * @return The root node of the loaded FXML.
     * @throws IOException If the FXML file cannot be loaded.
     */
    public Parent load(String fxmlPath) throws IOException {
        try (InputStream fxmlStream =  getClass().getResourceAsStream(fxmlPath)){
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(applicationContext::getBean);
            loader.setLocation(getClass().getResource(fxmlPath));
            return loader.load(fxmlStream);
        }
    }
}
