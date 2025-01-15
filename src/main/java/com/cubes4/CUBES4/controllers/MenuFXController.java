package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.annotation.FXMLController;
import com.cubes4.CUBES4.util.SceneManager;
import com.cubes4.CUBES4.util.SceneType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FXMLController
public class MenuFXController {

    private static final Logger log = LoggerFactory.getLogger(MenuFXController.class);
    private final SceneManager sceneManager;

    private boolean sidebarExpanded = false;

    @FXML
    private Button sidebarArticle;

    @FXML
    private Button sidebarFamilies;

    @FXML
    private Button sidebarCustomers;

    @FXML
    private Button sidebarOrder;

    @FXML
    private Button sidebarSupplier;

    @FXML
    private Button sidebarSettings;

    @FXML
    private AnchorPane mainContentArea;

    @FXML
    private Label headerTitleLabel;

    @FXML
    private AnchorPane sidebarContainer;

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

    public void expandSidebar() {
        if (sidebarExpanded) return;
        sidebarContainer.setPrefWidth(200.0);
        // sidebarArticle.setText("Articles");
        sidebarExpanded = true;
    }

    public void collapseSidebar() {
        // localToScreen() gets the absolute screen bounds of the AnchorPane
        var bounds = sidebarContainer.localToScreen(sidebarContainer.getBoundsInLocal());
        if (bounds == null) return;
        boolean outside = !bounds.contains(Screen.getPrimary().getOutputScaleX(), Screen.getPrimary().getOutputScaleY());
        if (outside && sidebarExpanded) {
            sidebarContainer.setPrefWidth(45.0);
            sidebarExpanded = false;
        }
    }

}
