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

    // Tailles prédéfinies
    private static final double SMALL_WIDTH = 600;
    private static final double SMALL_HEIGHT = 400;
    private static final double MEDIUM_WIDTH = 800;
    private static final double MEDIUM_HEIGHT = 600;
    private static final double LARGE_WIDTH = 1024;
    private static final double LARGE_HEIGHT = 768;

    // Champs pour gérer la taille globale de l'application
    private double appWidth = MEDIUM_WIDTH;  // Taille par défaut
    private double appHeight = MEDIUM_HEIGHT; // Taille par défaut

    // Champ pour la couleur de fond globale
    private String backgroundColor = "rgb(255, 255, 255)"; // Blanc par défaut

    public SceneManager(SpringFXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }

    public void setPrimaryStage(Stage stage) {
        if (stage == null) {
            throw new IllegalArgumentException("Primary stage cannot be null.");
        }
        this.primaryStage = stage;
        primaryStage.setWidth(appWidth);
        primaryStage.setHeight(appHeight);
    }

    public Stage getPrimaryStage() {
        if (primaryStage == null) {
            throw new IllegalStateException("Primary stage not set. Call setPrimaryStage() before accessing it.");
        }
        return primaryStage;
    }

    // Définir la taille de l'application par préréglage
    public void setAppSize(String sizePreset) {
        switch (sizePreset.toLowerCase()) {
            case "petit":
                this.appWidth = SMALL_WIDTH;
                this.appHeight = SMALL_HEIGHT;
                break;
            case "moyen":
                this.appWidth = MEDIUM_WIDTH;
                this.appHeight = MEDIUM_HEIGHT;
                break;
            case "grand":
                this.appWidth = LARGE_WIDTH;
                this.appHeight = LARGE_HEIGHT;
                break;
            default:
                throw new IllegalArgumentException("Invalid size preset: " + sizePreset);
        }

        if (primaryStage != null) {
            primaryStage.setWidth(appWidth);
            primaryStage.setHeight(appHeight);
        }
    }

    public void setBackgroundColor(String color) {
        this.backgroundColor = color;
    }

    public void switchScene(SceneType sceneType) {
        if (primaryStage == null) {
            throw new IllegalStateException("Primary stage not set. Call setPrimaryStage() before switching scenes.");
        }

        Scene scene = scenes.get(sceneType);
        if (scene == null) {
            try {
                Parent root = fxmlLoader.load(sceneType.getFxmlPath());
                scene = new Scene(root, appWidth, appHeight);
                scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
                root.setStyle("-fx-background-color: " + backgroundColor);
                scenes.put(sceneType, scene);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        } else {
            Parent root = scene.getRoot();
            root.setStyle("-fx-background-color: " + backgroundColor);
        }

        primaryStage.setScene(scene);
        primaryStage.setWidth(appWidth);
        primaryStage.setHeight(appHeight);
        primaryStage.setTitle(sceneType.getTitle());
        primaryStage.show();
    }

    public void registerScene(SceneType sceneType, Scene scene) {
        scenes.put(sceneType, scene);
    }
}
