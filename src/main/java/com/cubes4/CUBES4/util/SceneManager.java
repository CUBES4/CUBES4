package com.cubes4.CUBES4.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

@Component
public class SceneManager {

    private final ApplicationContext applicationContext;
    private Stage primaryStage;

    private Label headerTitleLabel;

    private AnchorPane mainContentArea;

    private final Map<SceneType, Node> viewCache = new WeakHashMap<>();

    private final Map<SceneType, Object> controllers = new HashMap<>();

    // Tailles prédéfinies
    private static final double SMALL_WIDTH = 600;
    private static final double SMALL_HEIGHT = 400;
    private static final double MEDIUM_WIDTH = 800;
    private static final double MEDIUM_HEIGHT = 600;
    private static final double LARGE_WIDTH = 1024;
    private static final double LARGE_HEIGHT = 768;

    // Champs pour gérer la taille globale de l'application
    private double appWidth = LARGE_WIDTH;  // Taille par défaut
    private double appHeight = LARGE_HEIGHT; // Taille par défaut

    // Champ pour la couleur de fond globale
    private String backgroundColor = "rgb(255, 255, 255)"; // Blanc par défaut

    public SceneManager(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void setHeaderTitleLabel(Label label) {
        this.headerTitleLabel = label;
    }

    /**
     * Sets the primary stage for the application.
     *
     * @param primaryStage the main stage of the JavaFX application
     */
    public void setPrimaryStage(Stage primaryStage) {
        if (primaryStage == null) {
            throw new IllegalArgumentException("Primary stage cannot be null.");
        }
        this.primaryStage = primaryStage;
        this.primaryStage.setWidth(appWidth);
        this.primaryStage.setHeight(appHeight);
    }

    public Stage getPrimaryStage() {
        if (primaryStage == null) {
            throw new IllegalStateException("Primary stage not set. Call setPrimaryStage() before accessing it.");
        }
        return primaryStage;
    }

    public void setMainContentArea(AnchorPane mainContentArea) {
        this.mainContentArea = mainContentArea;
    }

    // TODO Remove this
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

    /**
     * Switches the scene of the primary stage.
     *
     * @param sceneType the type of the scene to switch to
     */
    public void switchScene(SceneType sceneType) {
        if (primaryStage == null) {
            throw new IllegalStateException("Primary stage not set. Call setPrimaryStage() before switching scenes.");
        }

        try {
            FXMLLoader loader = createLoader(sceneType.getFxmlPath());
            Parent root = loader.load();
            Scene scene = new Scene(root, appWidth, appHeight);
            scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

            root.setStyle("-fx-background-color: " + backgroundColor);

            if (headerTitleLabel != null) headerTitleLabel.setText(sceneType.getTitle());

            controllers.put(sceneType, loader.getController());

            primaryStage.setScene(scene);
            primaryStage.setWidth(appWidth);
            primaryStage.setHeight(appHeight);
            primaryStage.setTitle(sceneType.getTitle());
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load scene: " + sceneType.getTitle(), e);
        }
    }

    /**
     * Loads a view into the specified content area.
     *
     * @param sceneType the type of the view to load
     */
    public void loadView(SceneType sceneType) {
        try {
            Node view = viewCache.get(sceneType);
            if (view == null) {
                FXMLLoader loader = createLoader(sceneType.getFxmlPath());
                view = loader.load();
                viewCache.put(sceneType, view);
            }
            if (headerTitleLabel != null) headerTitleLabel.setText(sceneType.getTitle());

            mainContentArea.getChildren().setAll(view);

            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load view: " + sceneType.getTitle(), e);
        }
    }

    public Object getController(SceneType sceneType) {
        return controllers.get(sceneType);
    }

    private FXMLLoader createLoader(String fxmlPath) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        return fxmlLoader;
    }
}
