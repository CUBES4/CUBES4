package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.annotation.FXMLController;
import com.cubes4.CUBES4.dto.ArticleDTO;
import com.cubes4.CUBES4.dto.FamilyDTO;
import com.cubes4.CUBES4.services.ArticleService;
import com.cubes4.CUBES4.services.FamilyService;
import com.cubes4.CUBES4.util.SceneManager;
import com.cubes4.CUBES4.util.SceneType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

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
    private TextField descriptionField;

    @FXML
    private TextField stockField;

    @FXML
    private TextField stockMinField;

    @FXML
    private TextField unitPriceField;

    @FXML
    private ComboBox<FamilyDTO> familyComboBox;

    @FXML
    private Button searchButton;

    @FXML
    private Button addButton;

    @FXML
    private Button refreshButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField searchField;

    private final ArticleService articleService;
    private final FamilyService familyService;
    private final SceneManager sceneManager;

    public ArticleFXController(ArticleService articleService, FamilyService familyService, SceneManager sceneManager) {
        this.articleService = articleService;
        this.familyService = familyService;
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());
        unitPriceColumn.setCellValueFactory(data -> data.getValue().unitPriceProperty().asObject());
        stockColumn.setCellValueFactory(data -> data.getValue().stockProperty().asObject());

        addEditColumn();
        addDeleteColumn();
        loadFamilies();

        refreshButton.setOnAction(event -> refreshData());
        addButton.setOnAction(event -> addArticle());
        backButton.setOnAction(event -> sceneManager.switchScene(SceneType.DASHBOARD));
        searchButton.setOnAction(event -> searchArticles());

        loadArticles();
    }

    private void refreshData() {
        // Recharger les articles et les familles
        loadArticles();
        loadFamilies();
    }

    public void loadArticlesByFamily(Long familyId) {
        List<ArticleDTO> articles = articleService.getArticlesByFamily(familyId);
        articleTable.getItems().setAll(articles);
    }

    private void loadArticles() {
        List<ArticleDTO> articles = articleService.getAllArticles();
        articleTable.getItems().setAll(articles);
        resetForm();
    }

    private void loadFamilies() {
        List<FamilyDTO> families = familyService.getAllFamilies();
        familyComboBox.setItems(FXCollections.observableArrayList(families));
        setupFamilyComboBox();
    }

    private void setupFamilyComboBox() {
        familyComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(FamilyDTO family) {
                return family != null ? family.getName() : "";
            }

            @Override
            public FamilyDTO fromString(String string) {
                return familyComboBox.getItems().stream()
                        .filter(family -> family.getName().equals(string))
                        .findFirst()
                        .orElse(null);
            }
        });
    }

    private void searchArticles() {
        String query = searchField.getText().trim().toLowerCase();
        if (query.isEmpty()) {
            loadArticles();
        } else {
            List<ArticleDTO> filteredArticles = articleService.getAllArticles().stream()
                    .filter(article -> article.getName().toLowerCase().contains(query))
                    .toList();
            articleTable.getItems().setAll(filteredArticles);
        }
    }

    private void addArticle() {
        if (!areFieldsValid()) {
            showAlert("Erreur", "Tous les champs doivent être remplis !");
            return;
        }

        FamilyDTO selectedFamily = familyComboBox.getValue();
        if (selectedFamily == null) {
            showAlert("Erreur", "Veuillez sélectionner une famille !");
            return;
        }

        ArticleDTO article = new ArticleDTO();
        article.setName(nameField.getText());
        article.setDescription(descriptionField.getText());
        article.setStock(Integer.parseInt(stockField.getText()));
        article.setStockMin(Integer.parseInt(stockMinField.getText()));
        article.setUnitPrice(Double.parseDouble(unitPriceField.getText()));
        article.setFamilyId(selectedFamily.getId());

        articleService.createArticle(article);
        loadArticles();
        loadFamilies(); // Recharger les familles après ajout
    }

    private boolean areFieldsValid() {
        return !nameField.getText().isEmpty() &&
                !descriptionField.getText().isEmpty() &&
                !stockField.getText().isEmpty() &&
                !stockMinField.getText().isEmpty() &&
                !unitPriceField.getText().isEmpty();
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
                setGraphic(empty ? null : editButton);
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
                setGraphic(empty ? null : deleteButton);
            }
        });
    }

    private void handleEditArticle(ArticleDTO article) {
        nameField.setText(article.getName());
        descriptionField.setText(article.getDescription());
        stockField.setText(String.valueOf(article.getStock()));
        stockMinField.setText(String.valueOf(article.getStockMin()));
        unitPriceField.setText(String.valueOf(article.getUnitPrice()));

        familyComboBox.getSelectionModel().select(familyService.getFamilyById(article.getFamilyId()));

        addButton.setText("Modifier");
        addButton.setOnAction(event -> updateArticle(article));
    }

    private void updateArticle(ArticleDTO article) {
        if (!areFieldsValid()) {
            showAlert("Erreur", "Tous les champs doivent être remplis !");
            return;
        }

        article.setName(nameField.getText());
        article.setDescription(descriptionField.getText());
        article.setStock(Integer.parseInt(stockField.getText()));
        article.setStockMin(Integer.parseInt(stockMinField.getText()));
        article.setUnitPrice(Double.parseDouble(unitPriceField.getText()));
        article.setFamilyId(familyComboBox.getValue().getId());

        articleService.updateArticle(article.getId(), article);
        loadArticles();
        loadFamilies(); // Recharger les familles après modification
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
        descriptionField.clear();
        stockField.clear();
        stockMinField.clear();
        unitPriceField.clear();
        familyComboBox.getSelectionModel().clearSelection();
        addButton.setText("Ajouter");
        addButton.setOnAction(event -> addArticle());
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
