package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.annotation.FXMLController;
import com.cubes4.CUBES4.util.SceneManager;
import com.cubes4.CUBES4.util.SceneType;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;

@FXMLController
public class DashboardFXController {

    @FXML
    private ImageView articlesIcon;

    @FXML
    private ImageView customersIcon;

    @FXML
    private ImageView ordersIcon;

    @FXML
    private ImageView suppliersIcon;

    @FXML
    private ImageView settingsIcon;

    @FXML
    private ImageView familiesIcon; // Nouvelle icône pour les familles

    @FXML
    private Button manageArticlesButton;

    @FXML
    private Button manageCustomersButton;

    @FXML
    private Button manageOrdersButton;

    @FXML
    private Button manageSuppliersButton;

    @FXML
    private Button manageFamiliesButton; // Nouveau bouton pour gérer les familles

    @FXML
    private Button settingsButton;

    private final SceneManager sceneManager;

    public DashboardFXController(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize() {
        // Charger les icônes
        articlesIcon.setImage(new Image(getClass().getResourceAsStream("/images/icon-articles.png")));
        customersIcon.setImage(new Image(getClass().getResourceAsStream("/images/icon-customers.png")));
        ordersIcon.setImage(new Image(getClass().getResourceAsStream("/images/icon-orders.png")));
        suppliersIcon.setImage(new Image(getClass().getResourceAsStream("/images/icon-suppliers.png")));
        familiesIcon.setImage(new Image(getClass().getResourceAsStream("/images/icon-settings.png"))); // Nouvelle icône
        settingsIcon.setImage(new Image(getClass().getResourceAsStream("/images/icon-settings.png")));

        // Actions des boutons
        manageArticlesButton.setOnAction(event -> sceneManager.switchScene(SceneType.MANAGE_ARTICLES));
        manageCustomersButton.setOnAction(event -> sceneManager.switchScene(SceneType.MANAGE_CUSTOMERS));
        manageOrdersButton.setOnAction(event -> sceneManager.switchScene(SceneType.MANAGE_ORDERS));
        manageSuppliersButton.setOnAction(event -> sceneManager.switchScene(SceneType.MANAGE_SUPPLIERS));
        manageFamiliesButton.setOnAction(event -> sceneManager.switchScene(SceneType.MANAGE_FAMILIES)); // Action pour familles
        settingsButton.setOnAction(event -> sceneManager.switchScene(SceneType.SETTINGS));
    }
}
