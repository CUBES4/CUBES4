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
    private FXMLLoader currentLoader; // Pour stocker le dernier FXMLLoader utilisé

    public SpringFXMLLoader(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Parent load(String fxmlPath) throws IOException {
        try (InputStream fxmlStream = getClass().getResourceAsStream(fxmlPath)) {
            currentLoader = new FXMLLoader();
            currentLoader.setControllerFactory(applicationContext::getBean);
            currentLoader.setLocation(getClass().getResource(fxmlPath));
            return currentLoader.load(fxmlStream);
        }
    }

    public Object getController() {
        if (currentLoader == null) {
            throw new IllegalStateException("FXMLLoader has not been initialized. Load an FXML file first.");
        }
        return currentLoader.getController();
    }
}
