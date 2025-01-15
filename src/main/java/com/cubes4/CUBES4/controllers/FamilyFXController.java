package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.annotation.FXMLController;
import com.cubes4.CUBES4.dto.FamilyDTO;
import com.cubes4.CUBES4.services.FamilyService;
import com.cubes4.CUBES4.util.SceneManager;
import com.cubes4.CUBES4.util.SceneType;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

@FXMLController
public class FamilyFXController {

    @FXML
    private TableView<FamilyDTO> familyTable;

    @FXML
    private TableColumn<FamilyDTO, Long> idColumn;

    @FXML
    private TableColumn<FamilyDTO, String> nameColumn;

    @FXML
    private TextField nameField;

    @FXML
    private Button addButton;

    @FXML
    private Button refreshButton;

    @FXML
    private Button backButton;

    private final FamilyService familyService;
    private final SceneManager sceneManager;

    public FamilyFXController(FamilyService familyService, SceneManager sceneManager) {
        this.familyService = familyService;
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(data -> new SimpleLongProperty(data.getValue().getId()).asObject());
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));

        refreshButton.setOnAction(event -> loadFamilies());
        addButton.setOnAction(event -> addFamily());
        backButton.setOnAction(event -> sceneManager.loadView(SceneType.DASHBOARD));

        familyTable.setRowFactory(tv -> {
            TableRow<FamilyDTO> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 2) {
                    FamilyDTO clickedFamily = row.getItem();
                    viewArticlesByFamily(clickedFamily.getId());
                }
            });
            return row;
        });

        loadFamilies();
    }

    private void loadFamilies() {
        List<FamilyDTO> families = familyService.getAllFamilies();
        familyTable.getItems().setAll(families);
    }

    private void addFamily() {
        String familyName = nameField.getText().trim();
        if (!familyName.isEmpty()) {
            try {
                FamilyDTO newFamily = new FamilyDTO();
                newFamily.setName(familyName);
                familyService.createFamily(newFamily);
                loadFamilies();
                nameField.clear();
            } catch (IllegalArgumentException e) {
                showAlert("Erreur", e.getMessage());
            }
        } else {
            showAlert("Erreur", "Le nom de la famille ne peut pas être vide.");
        }
    }

    private void viewArticlesByFamily(Long familyId) {
        ArticleFXController articleController = (ArticleFXController) sceneManager.getController(SceneType.MANAGE_ARTICLES);
        if (articleController != null) {
            articleController.loadArticlesByFamily(familyId);
        }
        sceneManager.loadView(SceneType.MANAGE_ARTICLES);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
