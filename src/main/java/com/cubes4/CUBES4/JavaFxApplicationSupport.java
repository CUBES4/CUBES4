package com.cubes4.CUBES4;

import com.cubes4.CUBES4.config.SpringFXMLLoader;
import com.cubes4.CUBES4.util.SceneManager;
import com.cubes4.CUBES4.util.SceneType;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JavaFxApplicationSupport extends Application {

    private static ConfigurableApplicationContext springContext;
    private static SpringFXMLLoader springFXMLLoader;

    @Override
    public void init() {
        springContext = new SpringApplicationBuilder(JavaFxApplicationSupport.class)
                .run(getParameters().getRaw().toArray(new String[0]));
        springFXMLLoader = springContext.getBean(SpringFXMLLoader.class);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            SceneManager sceneManager = springContext.getBean(SceneManager.class);
            sceneManager.setPrimaryStage(primaryStage);

            Parent root = springFXMLLoader.load("/fxml/dashboard.fxml");
            Scene scene = new Scene(root, 800, 600);
            scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
            sceneManager.registerScene(SceneType.DASHBOARD, scene);

            primaryStage.setScene(scene);
            primaryStage.setTitle("CUBES4 - Dashboard");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        springContext.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
