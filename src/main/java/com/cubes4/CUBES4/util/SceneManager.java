package com.cubes4.CUBES4.util;

import com.cubes4.CUBES4.config.SpringFXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@Component
public class SceneManager {

    private final SpringFXMLLoader fxmlLoader;
    private Stage primaryStage;
    private final Map<SceneType, Scene> scenes = new HashMap<>();

    public SceneManager(SpringFXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }

    /**
     * Sets the primary stage. Must be called before switching scenes.
     *
     * @param stage The primary stage of the application.
     */
    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    /**
     * Loads a scene based on the provided SceneType.
     * If the scene is already loaded, it reuses the existing one.
     *
     * @param sceneType The type of the scene to load.
     */
    public void switchScene(SceneType sceneType) {
        if (primaryStage == null) {
            throw new IllegalStateException("Primary stage not set. Call setPrimaryStage() before switching scenes.");
        }

        Scene scene = scenes.get(sceneType);
        if (scene == null) {
            try {
                Parent root = fxmlLoader.load(sceneType.getFxmlPath());
                scene = new Scene(root, 800, 600);
                scenes.put(sceneType, scene);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        primaryStage.setScene(scene);
        primaryStage.setTitle(sceneType.getTitle());
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
