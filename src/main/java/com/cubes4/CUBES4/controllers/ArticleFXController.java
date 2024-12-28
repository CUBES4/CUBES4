package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.annotation.FXMLController;
import com.cubes4.CUBES4.dto.ArticleDTO;
import com.cubes4.CUBES4.services.ArticleService;
import com.cubes4.CUBES4.util.SceneManager;
import com.cubes4.CUBES4.util.SceneType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.util.List;

@FXMLController
public class ArticleFXController {

    @FXML
    private TableView<ArticleDTO> articleTable;

    @FXML
    private TableColumn<ArticleDTO, String> nameColumn;

    @FXML
    private TableColumn<ArticleDTO, Double> unitPriceColumn;

    @FXML
    private TableColumn<ArticleDTO, Integer> stockColumn;

    @FXML
    private TableColumn<ArticleDTO, Void> editColumn;

    @FXML
    private TableColumn<ArticleDTO, Void> deleteColumn;

    @FXML
    private TextField nameField;

    @FXML
    private TextField unitPriceField;

    @FXML
    private TextField stockField;

    @FXML
    private Button addButton;

    @FXML
    private Button refreshButton;

    @FXML
    private Button backButton;

    private final ArticleService articleService;
    private final SceneManager sceneManager;

    public ArticleFXController(ArticleService articleService, SceneManager sceneManager) {
        this.articleService = articleService;
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());
        unitPriceColumn.setCellValueFactory(data -> data.getValue().unitPriceProperty().asObject());
        stockColumn.setCellValueFactory(data -> data.getValue().stockProperty().asObject());

        addEditColumn();
        addDeleteColumn();

        refreshButton.setOnAction(event -> loadArticles());
        addButton.setOnAction(event -> addArticle());
        backButton.setOnAction(event -> sceneManager.switchScene(SceneType.DASHBOARD));

        // Appliquer une RowFactory pour personnaliser les lignes en fonction du stock
        articleTable.setRowFactory(tv -> new TableRow<>() {
            @Override
            protected void updateItem(ArticleDTO article, boolean empty) {
                super.updateItem(article, empty);

                if (article == null || empty) {
                    setStyle("");
                } else if (article.getStock() == 0) {
                    setStyle("-fx-background-color: red; -fx-text-fill: white;");
                } else if (article.getStock() < 10) {
                    setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
                } else {
                    setStyle("");
                }
            }
        });

        loadArticles();
    }

    private void loadArticles() {
        List<ArticleDTO> articles = articleService.getAllArticles();
        articleTable.getItems().setAll(articles);
        resetForm();
    }

    private void addArticle() {
        if (!areFieldsValid()) {
            return;
        }

        ArticleDTO article = new ArticleDTO();
        article.setName(nameField.getText());
        article.setUnitPrice(Double.parseDouble(unitPriceField.getText()));
        article.setStock(Integer.parseInt(stockField.getText()));

        articleService.createArticle(article);
        loadArticles();
    }

    private boolean areFieldsValid() {
        return !nameField.getText().isEmpty() &&
                !unitPriceField.getText().isEmpty() &&
                !stockField.getText().isEmpty();
    }

    private void addEditColumn() {
        editColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Modifier");

            {
                editButton.setOnAction(event -> {
                    ArticleDTO article = getTableView().getItems().get(getIndex());
                    handleEditArticle(article);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(editButton);
                }
            }
        });
    }

    private void addDeleteColumn() {
        deleteColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Supprimer");

            {
                deleteButton.setOnAction(event -> {
                    ArticleDTO article = getTableView().getItems().get(getIndex());
                    handleDeleteArticle(article);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });
    }

    private void handleEditArticle(ArticleDTO article) {
        nameField.setText(article.getName());
        unitPriceField.setText(String.valueOf(article.getUnitPrice()));
        stockField.setText(String.valueOf(article.getStock()));

        addButton.setText("Modifier");
        addButton.setOnAction(event -> updateArticle(article));
    }

    private void updateArticle(ArticleDTO article) {
        if (!areFieldsValid()) {
            return;
        }

        article.setName(nameField.getText());
        article.setUnitPrice(Double.parseDouble(unitPriceField.getText()));
        article.setStock(Integer.parseInt(stockField.getText()));

        articleService.updateArticle(article.getId(), article);
        loadArticles();
    }

    private void handleDeleteArticle(ArticleDTO article) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous vraiment supprimer cet article ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                articleService.deleteArticle(article.getId());
                loadArticles();
            }
        });
    }

    private void resetForm() {
        nameField.clear();
        unitPriceField.clear();
        stockField.clear();
        addButton.setText("Ajouter");
        addButton.setOnAction(event -> addArticle());
    }
}
