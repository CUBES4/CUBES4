package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.annotation.FXMLController;
import com.cubes4.CUBES4.models.Article;
import com.cubes4.CUBES4.services.ArticleService;
import com.cubes4.CUBES4.util.SceneManager;
import com.cubes4.CUBES4.util.SceneType;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

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
    private ImageView familiesIcon;

    @FXML
    private Button manageArticlesButton;

    @FXML
    private Button manageCustomersButton;

    @FXML
    private Button manageOrdersButton;

    @FXML
    private Button manageSuppliersButton;

    @FXML
    private Button manageFamiliesButton;

    @FXML
    private Button settingsButton;

    private final SceneManager sceneManager;
    private final ArticleService articleService; // Service pour les articles

    public DashboardFXController(SceneManager sceneManager, ArticleService articleService) {
        this.sceneManager = sceneManager;
        this.articleService = articleService; // Injection du service pour gérer les articles
    }

    @FXML
    public void initialize() {
        // Charger les icônes
        articlesIcon.setImage(new Image(getClass().getResourceAsStream("/images/icon-articles.png")));
        customersIcon.setImage(new Image(getClass().getResourceAsStream("/images/icon-customers.png")));
        ordersIcon.setImage(new Image(getClass().getResourceAsStream("/images/icon-orders.png")));
        suppliersIcon.setImage(new Image(getClass().getResourceAsStream("/images/icon-suppliers.png")));
        familiesIcon.setImage(new Image(getClass().getResourceAsStream("/images/icon-settings.png")));
        settingsIcon.setImage(new Image(getClass().getResourceAsStream("/images/icon-settings.png")));

        // Actions des boutons
        manageArticlesButton.setOnAction(event -> sceneManager.switchScene(SceneType.MANAGE_ARTICLES));
        manageCustomersButton.setOnAction(event -> sceneManager.switchScene(SceneType.MANAGE_CUSTOMERS));
        manageOrdersButton.setOnAction(event -> sceneManager.switchScene(SceneType.MANAGE_ORDERS));
        manageSuppliersButton.setOnAction(event -> sceneManager.switchScene(SceneType.MANAGE_SUPPLIERS));
        manageFamiliesButton.setOnAction(event -> sceneManager.switchScene(SceneType.MANAGE_FAMILIES));
        settingsButton.setOnAction(event -> sceneManager.switchScene(SceneType.SETTINGS));

        // Vérifier les articles sous le stock minimum
        checkLowStockArticles();
    }

    /**
     * Vérifie les articles sous le stock minimum et affiche une alerte.
     */
    private void checkLowStockArticles() {
        try {
            List<Article> lowStockArticles = articleService.findArticlesBelowStockMin();

            if (lowStockArticles.isEmpty()) {
                // Aucun article sous le stock minimum
                return;
            }

            if (lowStockArticles.size() <= 3) {
                // Si 3 articles ou moins, afficher leurs noms
                StringBuilder alertMessage = new StringBuilder("Attention, les articles suivants sont en dessous du stock minimum :\n");
                for (Article article : lowStockArticles) {
                    alertMessage.append("- ").append(article.getName()).append("\n");
                }
                showAlert(alertMessage.toString());
            } else {
                // Si plus de 3 articles, afficher uniquement le nombre
                showAlert("Attention : " + lowStockArticles.size() + " articles sont en dessous du stock minimum.");
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la vérification des stocks : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Affiche une alerte avec le message spécifié.
     *
     * @param message Message à afficher
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING, message, ButtonType.OK);
        alert.showAndWait();
    }
}
