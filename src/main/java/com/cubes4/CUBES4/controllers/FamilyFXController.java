package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.annotation.FXMLController;
import com.cubes4.CUBES4.dto.FamilyDTO;
import com.cubes4.CUBES4.services.FamilyService;
import com.cubes4.CUBES4.util.SceneManager;
import com.cubes4.CUBES4.util.SceneType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

@FXMLController
public class FamilyFXController {

    @FXML
    private TableView<FamilyDTO> familyTable;

    @FXML
    private TableColumn<FamilyDTO, String> nameColumn;

    @FXML
    private TableColumn<FamilyDTO, Void> editColumn;

    @FXML
    private TableColumn<FamilyDTO, Void> deleteColumn;

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

    private FamilyDTO selectedFamily; // Pour suivre la famille en cours de modification

    public FamilyFXController(FamilyService familyService, SceneManager sceneManager) {
        this.familyService = familyService;
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize() {
        // Configurer les colonnes du tableau
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        addEditColumn();
        addDeleteColumn();

        // Configurer les actions des boutons
        refreshButton.setOnAction(event -> loadFamilies());
        addButton.setOnAction(event -> handleAddOrUpdate());
        backButton.setOnAction(event -> sceneManager.switchScene(SceneType.DASHBOARD));

        // Charger les familles au démarrage
        loadFamilies();
    }

    private void loadFamilies() {
        List<FamilyDTO> families = familyService.getAllFamilies();
        familyTable.setItems(FXCollections.observableArrayList(families));
        resetForm();
    }

    private void handleAddOrUpdate() {
        if (nameField.getText().isEmpty()) {
            showAlert("Erreur", "Le champ Nom est requis !");
            return;
        }

        if (selectedFamily == null) {
            // Ajouter une nouvelle famille
            FamilyDTO newFamily = new FamilyDTO();
            newFamily.setName(nameField.getText());
            familyService.createFamily(newFamily);
        } else {
            // Mettre à jour la famille existante
            selectedFamily.setName(nameField.getText());
            familyService.updateFamily(selectedFamily.getId(), selectedFamily);
        }

        loadFamilies();
    }

    private void addEditColumn() {
        editColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Modifier");

            {
                editButton.setOnAction(event -> {
                    FamilyDTO family = getTableView().getItems().get(getIndex());
                    handleEditFamily(family);
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

    private void handleEditFamily(FamilyDTO family) {
        selectedFamily = family;
        nameField.setText(family.getName());
        addButton.setText("Modifier");
    }

    private void addDeleteColumn() {
        deleteColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Supprimer");

            {
                deleteButton.setOnAction(event -> {
                    FamilyDTO family = getTableView().getItems().get(getIndex());
                    handleDeleteFamily(family);
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

    private void handleDeleteFamily(FamilyDTO family) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous vraiment supprimer cette famille ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                familyService.deleteFamily(family.getId());
                loadFamilies();
            }
        });
    }

    private void resetForm() {
        nameField.clear();
        selectedFamily = null;
        addButton.setText("Ajouter");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
