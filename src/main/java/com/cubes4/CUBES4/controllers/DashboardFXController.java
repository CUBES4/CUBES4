package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.annotation.FXMLController;
import com.cubes4.CUBES4.util.SceneManager;
import com.cubes4.CUBES4.util.SceneType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@FXMLController
public class DashboardFXController {

    private final SceneManager sceneManager;

    public DashboardFXController(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    private Button manageArticlesButton;

    @FXML
    private Button manageCustomersButton;

    @FXML
    private Button manageOrdersButton;

    @FXML
    public void initialize() {
        manageArticlesButton.setOnAction(event -> handleManageArticles());
        manageCustomersButton.setOnAction(event -> handleManageCustomers());
        manageOrdersButton.setOnAction(event -> handleManageOrders());
    }

    private void handleManageArticles() {
        sceneManager.switchScene(SceneType.MANAGE_ARTICLES);
    }

    private void handleManageCustomers() {
        sceneManager.switchScene(SceneType.MANAGE_CUSTOMERS);
    }

    private void handleManageOrders() {
        sceneManager.switchScene(SceneType.MANAGE_ORDERS);
    }

}
