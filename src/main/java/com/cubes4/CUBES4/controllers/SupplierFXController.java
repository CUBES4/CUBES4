package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.annotation.FXMLController;
import com.cubes4.CUBES4.dto.SupplierDTO;
import com.cubes4.CUBES4.services.SupplierService;
import com.cubes4.CUBES4.util.SceneManager;
import com.cubes4.CUBES4.util.SceneType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.stream.Collectors;

import java.util.List;

@FXMLController
public class SupplierFXController {

    @FXML
    private TableView<SupplierDTO> supplierTable;

    @FXML
    private TableColumn<SupplierDTO, String> supplierNameColumn;

    @FXML
    private TableColumn<SupplierDTO, String> emailColumn;

    @FXML
    private TableColumn<SupplierDTO, String> phoneNumberColumn;

    @FXML
    private TableColumn<SupplierDTO, String> addressColumn;

    @FXML
    private TableColumn<SupplierDTO, Void> editColumn;

    @FXML
    private TableColumn<SupplierDTO, Void> deleteColumn;

    @FXML
    private TextField supplierNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField searchField;

    @FXML
    private Button addButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button refreshButton;

    @FXML
    private Button backButton;

    private final SupplierService supplierService;
    private final SceneManager sceneManager;

    public SupplierFXController(SupplierService supplierService, SceneManager sceneManager) {
        this.supplierService = supplierService;
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize() {
        supplierNameColumn.setCellValueFactory(data -> data.getValue().supplierNameProperty());
        emailColumn.setCellValueFactory(data -> data.getValue().emailProperty());
        phoneNumberColumn.setCellValueFactory(data -> data.getValue().phoneNumberProperty());
        addressColumn.setCellValueFactory(data -> data.getValue().addressProperty());

        addEditColumn();
        addDeleteColumn();

        refreshButton.setOnAction(event -> loadSuppliers());
        addButton.setOnAction(event -> addSupplier());
        searchButton.setOnAction(event -> searchSuppliers());
        backButton.setOnAction(event -> sceneManager.switchScene(SceneType.DASHBOARD));

        loadSuppliers();
    }

    private void loadSuppliers() {
        List<SupplierDTO> suppliers = supplierService.getAllSuppliers();
        supplierTable.getItems().setAll(suppliers);
        resetForm();
    }

    private void addSupplier() {
        if (!areFieldsValid()) {
            return;
        }

        SupplierDTO supplier = new SupplierDTO();
        supplier.setSupplierName(supplierNameField.getText());
        supplier.setEmail(emailField.getText());
        supplier.setPhoneNumber(phoneNumberField.getText());
        supplier.setAddress(addressField.getText());

        supplierService.createSupplier(supplier);
        loadSuppliers();
    }

    private boolean areFieldsValid() {
        if (supplierNameField.getText().isEmpty() || emailField.getText().isEmpty() ||
                phoneNumberField.getText().isEmpty() || addressField.getText().isEmpty()) {
            showAlert("Erreur", "Tous les champs doivent être remplis.");
            return false;
        }

        if (!emailField.getText().matches("^[^@\\s]+@[^@\\s]+\\.[a-zA-Z]{2,6}$")) {
            showAlert("Erreur", "Adresse email invalide.");
            return false;
        }

        if (!phoneNumberField.getText().matches("\\d{10}")) {
            showAlert("Erreur", "Le numéro de téléphone doit contenir exactement 10 chiffres.");
            return false;
        }

        return true;
    }

    private void addEditColumn() {
        editColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Modifier");

            {
                editButton.setOnAction(event -> {
                    SupplierDTO supplier = getTableView().getItems().get(getIndex());
                    handleEditSupplier(supplier);
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
                    SupplierDTO supplier = getTableView().getItems().get(getIndex());
                    handleDeleteSupplier(supplier);
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

    private void handleEditSupplier(SupplierDTO supplier) {
        supplierNameField.setText(supplier.getSupplierName());
        emailField.setText(supplier.getEmail());
        phoneNumberField.setText(supplier.getPhoneNumber());
        addressField.setText(supplier.getAddress());

        addButton.setText("Modifier");
        addButton.setOnAction(event -> updateSupplier(supplier));
    }

    private void updateSupplier(SupplierDTO supplier) {
        if (!areFieldsValid()) {
            return;
        }

        supplier.setSupplierName(supplierNameField.getText());
        supplier.setEmail(emailField.getText());
        supplier.setPhoneNumber(phoneNumberField.getText());
        supplier.setAddress(addressField.getText());

        supplierService.updateSupplier(supplier.getId(), supplier);
        loadSuppliers();
    }

    private void handleDeleteSupplier(SupplierDTO supplier) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous vraiment supprimer ce fournisseur ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                supplierService.deleteSupplier(supplier.getId());
                loadSuppliers();
            }
        });
    }

    private void searchSuppliers() {
        String query = searchField.getText().trim().toLowerCase();
        if (query.isEmpty()) {
            loadSuppliers();
        } else {
            List<SupplierDTO> filteredSuppliers = supplierService.getAllSuppliers().stream()
                    .filter(s -> s.getSupplierName().toLowerCase().contains(query))
                    .collect(Collectors.toList());
            supplierTable.getItems().setAll(filteredSuppliers);
        }
    }

    private void resetForm() {
        supplierNameField.clear();
        emailField.clear();
        phoneNumberField.clear();
        addressField.clear();
        searchField.clear();
        addButton.setText("Ajouter");
        addButton.setOnAction(event -> addSupplier());
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
