package com.cubes4.CUBES4;

import com.cubes4.CUBES4.util.SceneManager;
import com.cubes4.CUBES4.util.SceneType;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@SpringBootApplication
public class JavaFxApplicationSupport extends javafx.application.Application {

    private static ConfigurableApplicationContext springContext;

    @Override
    public void init() {
        springContext = new SpringApplicationBuilder(JavaFxApplicationSupport.class)
                .run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void start(Stage primaryStage) {
        SceneManager sceneManager = springContext.getBean(SceneManager.class);
        sceneManager.setPrimaryStage(primaryStage);
        sceneManager.switchScene(SceneType.DASHBOARD);
    }

    @Override
    public void stop() {
        springContext.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
