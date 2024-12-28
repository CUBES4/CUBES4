package com.cubes4.CUBES4.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class SpringFXMLLoader {

    private final ApplicationContext applicationContext;

    public SpringFXMLLoader(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Parent load(String fxmlPath) throws IOException {
        try (InputStream fxmlStream = getClass().getResourceAsStream(fxmlPath)) {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(applicationContext::getBean);
            loader.setLocation(getClass().getResource(fxmlPath));
            return loader.load(fxmlStream);
        }
    }
}
