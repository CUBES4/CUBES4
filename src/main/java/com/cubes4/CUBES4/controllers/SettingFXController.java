package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.annotation.FXMLController;
import com.cubes4.CUBES4.util.SceneManager;
import com.cubes4.CUBES4.util.SceneType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

@FXMLController
public class SettingFXController {
    @FXML
    private Button applyButton;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private ComboBox<String> sizeComboBox;

    @FXML
    private Button backButton;

    private final SceneManager sceneManager;

    public SettingFXController(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize() {
        sizeComboBox.getItems().addAll("Petit", "Moyen", "Grand");
        sizeComboBox.setValue("Moyen"); // Par défaut à "Moyen"

        applyButton.setOnAction(event -> applySettings());
        backButton.setOnAction(event -> sceneManager.loadView(SceneType.DASHBOARD));
    }

    private void applySettings() {
        // Changer la taille de l'application
        String selectedSize = sizeComboBox.getValue();
        sceneManager.setAppSize(selectedSize);

        // Changer la couleur de fond
        Color selectedColor = colorPicker.getValue();
        String colorString = toRgbString(selectedColor);
        sceneManager.setBackgroundColor(colorString);

        // Appliquer immédiatement à la scène courante
        Stage stage = sceneManager.getPrimaryStage();
        stage.getScene().getRoot().setStyle("-fx-background-color: " + colorString);
    }

    private String toRgbString(Color color) {
        return String.format("rgb(%d, %d, %d)",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}
