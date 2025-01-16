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
import java.util.Map;
import java.util.WeakHashMap;

@Component
public class SceneManager {

    private final ApplicationContext applicationContext;

    private Stage primaryStage;
    private final Map<SceneType, Node> viewCache = new WeakHashMap<>();

    private Label headerTitleLabel;
    private AnchorPane mainContentArea;

    private static final double LARGE_WIDTH = 1024;
    private static final double LARGE_HEIGHT = 768;

    public SceneManager(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void setHeaderTitleLabel(Label label) {
        this.headerTitleLabel = label;
    }

    public void setMainContentArea(AnchorPane mainContentArea) {
        this.mainContentArea = mainContentArea;
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
    }

    public Stage getPrimaryStage() {
        if (primaryStage == null) {
            throw new IllegalStateException("Primary stage not set. Call setPrimaryStage() before accessing it.");
        }
        return primaryStage;
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
            Scene scene = new Scene(root);

            String css = getClass().getResource("/css/styles.css").toExternalForm();
            scene.getStylesheets().add(css);

            if (headerTitleLabel != null) headerTitleLabel.setText(sceneType.getTitle());

            primaryStage.setScene(scene);
            primaryStage.setTitle(sceneType.getTitle());
            primaryStage.setMaximized(false);
            primaryStage.setMaximized(true);
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

            Scene currentScene = mainContentArea.getScene();
            if (currentScene != null) {
                String css = getClass().getResource("/css/styles.css").toExternalForm();

                if (!currentScene.getStylesheets().contains(css)) currentScene.getStylesheets().add(css);
            }

            primaryStage.setMaximized(true);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load view: " + sceneType.getTitle(), e);
        }
    }

    private FXMLLoader createLoader(String fxmlPath) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        return fxmlLoader;
    }
}
