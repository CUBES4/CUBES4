package com.cubes4.CUBES4;

import com.cubes4.CUBES4.util.SceneManager;
import com.cubes4.CUBES4.util.SceneType;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JavaFxApplicationSupport extends Application {

    private static ConfigurableApplicationContext springContext;

    @Override
    public void init() {
        // Initialisation du contexte Spring
        springContext = new SpringApplicationBuilder(JavaFxApplicationSupport.class)
                .run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Récupération du SceneManager depuis le contexte Spring
            SceneManager sceneManager = springContext.getBean(SceneManager.class);
            sceneManager.setPrimaryStage(primaryStage);

            // Démarrage sur la scène de connexion
            sceneManager.switchScene(SceneType.LOGIN);

            primaryStage.setTitle("Connexion");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        // Arrêt du contexte Spring
        springContext.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
