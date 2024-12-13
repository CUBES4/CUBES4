package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.annotation.FXMLController;
import com.cubes4.CUBES4.dto.ArticleDTO;
import com.cubes4.CUBES4.services.ArticleService;
import com.cubes4.CUBES4.util.SceneManager;
import com.cubes4.CUBES4.util.SceneType;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@FXMLController
public class ArticleFXController {

    private final SceneManager sceneManager;

    @Autowired
    private ArticleService articleService;

    @FXML
    private ListView<String> articleList;

    @FXML
    private Button refreshButton;

    @FXML
    private TextField nameField;
    @FXML
    private TextField unitPriceField;
    @FXML
    private TextField stockField;
    @FXML
    private Button addButton;

    @FXML
    private Button backButton;

    @Autowired
    public ArticleFXController(SceneManager manager) {
        this.sceneManager = manager;
    }

    @FXML
    public void initialize() {
        refreshButton.setOnAction(e -> loadArticles());
        addButton.setOnAction(e -> addArticle());
        backButton.setOnAction(e -> handleBackToDashboard());
        loadArticles();
    }

    /**
     * Loads articles from the database and displays them in the ListView.
     */
    private void loadArticles() {
        try {
            articleList.getItems().clear();
            List<ArticleDTO> articles = articleService.getAllArticles();
            if (articles.isEmpty()) {
                showAlert("No Articles", "No articles found in the database.");
            } else {
                for (ArticleDTO article : articles) {
                    articleList.getItems().add(article.getName() + " - $" + article.getUnitPrice());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load articles: " + e.getMessage());
        }
    }

    /**
     * Adds a new article to the database based on user input.
     */
    private void addArticle() {
        String name = nameField.getText().trim();
        String unitPriceText = unitPriceField.getText().trim();
        String stockText = stockField.getText().trim();

        // Input validation
        if (name.isEmpty() || unitPriceText.isEmpty() || stockText.isEmpty()) {
            showAlert("Input Error", "Please fill in all fields.");
            return;
        }

        double unitPrice;
        int stock;

        try {
            unitPrice = Double.parseDouble(unitPriceText);
            stock = Integer.parseInt(stockText);
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Unit Price must be a number and Stock must be an integer.");
            return;
        }

        // Create ArticleDTO
        ArticleDTO newArticle = new ArticleDTO();
        newArticle.setName(name);
        newArticle.setUnitPrice(unitPrice);
        newArticle.setStock(stock);

        try {
            // Save the new article
            articleService.createArticle(newArticle);
            showAlert("Success", "Article added successfully.");

            // Clear input fields
            nameField.clear();
            unitPriceField.clear();
            stockField.clear();

            // Refresh the article list
            loadArticles();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to add article: " + e.getMessage());
        }
    }

    /**
     * Displays an alert dialog to the user.
     *
     * @param title   The title of the alert.
     * @param message The message content of the alert.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void handleBackToDashboard() {
        sceneManager.switchScene(SceneType.DASHBOARD);
    }
}
