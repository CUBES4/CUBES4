package com.cubes4.CUBES4.util;

import com.cubes4.CUBES4.config.SpringFXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class SceneManager {

    private final SpringFXMLLoader fxmlLoader;
    private Stage primaryStage;
    private final Map<SceneType, Scene> scenes = new HashMap<>();

    public SceneManager(SpringFXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }

    public void setPrimaryStage(Stage stage) {
        if (stage == null) {
            throw new IllegalArgumentException("Primary stage cannot be null.");
        }
        this.primaryStage = stage;
    }

    public void switchScene(SceneType sceneType) {
        if (primaryStage == null) {
            throw new IllegalStateException("Primary stage not set. Call setPrimaryStage() before switching scenes.");
        }

        Scene scene = scenes.get(sceneType);
        if (scene == null) {
            try {
                Parent root = fxmlLoader.load(sceneType.getFxmlPath());
                scene = new Scene(root, 800, 600);
                scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
                scenes.put(sceneType, scene);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        primaryStage.setScene(scene);
        primaryStage.setTitle(sceneType.getTitle());
        primaryStage.show();
    }

    public void registerScene(SceneType sceneType, Scene scene) {
        scenes.put(sceneType, scene);
    }
}
