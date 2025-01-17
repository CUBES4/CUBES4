package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.annotation.FXMLController;
import com.cubes4.CUBES4.util.SceneManager;
import com.cubes4.CUBES4.util.SceneType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

@FXMLController
public class MenuFXController {

    private final SceneManager sceneManager;

    @FXML
    private Button sidebarArticles, sidebarFamilies,
            sidebarCustomers, sidebarOrders,
            sidebarSuppliers, sidebarSettings,
            sidebarDashboard;
    @FXML
    private Label headerTitleLabel, sidebarDashboardText,
            sidebarArticlesText, sidebarFamiliesText,
            sidebarCustomersText, sidebarOrdersText,
            sidebarSuppliersText, sidebarSettingsText;

    @FXML
    private AnchorPane mainContentArea, sidebarContainer;

    @FXML
    private VBox sidebarVBox;

    public MenuFXController(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize() {
        sceneManager.setMainContentArea(mainContentArea);
        sceneManager.setHeaderTitleLabel(headerTitleLabel);

        sceneManager.loadView(SceneType.DASHBOARD);
    }

    @FXML
    public void loadHome() {
        sceneManager.loadView(SceneType.DASHBOARD);
    }

    @FXML
    public void loadArticles() {
        sceneManager.loadView(SceneType.MANAGE_ARTICLES);
    }

    @FXML
    public void loadFamilies() {
        sceneManager.loadView(SceneType.MANAGE_FAMILIES);
    }

    @FXML
    public void loadCustomers() {
        sceneManager.loadView(SceneType.MANAGE_CUSTOMERS);
    }

    @FXML
    public void loadOrders() {
        sceneManager.loadView(SceneType.MANAGE_ORDERS);
    }

    @FXML
    public void loadSuppliers() {
        sceneManager.loadView(SceneType.MANAGE_SUPPLIERS);
    }

    @FXML
    public void loadSettings() {
        sceneManager.loadView(SceneType.SETTINGS);
    }

}
