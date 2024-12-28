package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.annotation.FXMLController;
import com.cubes4.CUBES4.dto.CustomerDTO;
import com.cubes4.CUBES4.services.CustomerService;
import com.cubes4.CUBES4.util.SceneManager;
import com.cubes4.CUBES4.util.SceneType;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;
import java.util.stream.Collectors;

@FXMLController
public class CustomerFXController {

    @FXML
    private TableView<CustomerDTO> customerTable;

    @FXML
    private TableColumn<CustomerDTO, String> firstNameColumn;

    @FXML
    private TableColumn<CustomerDTO, String> lastNameColumn;

    @FXML
    private TableColumn<CustomerDTO, String> emailColumn;

    @FXML
    private TableColumn<CustomerDTO, String> addressColumn;

    @FXML
    private TableColumn<CustomerDTO, String> phoneNumberColumn;

    @FXML
    private TableColumn<CustomerDTO, Void> editColumn;

    @FXML
    private TableColumn<CustomerDTO, Void> deleteColumn;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField phoneNumberField;

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

    private final CustomerService customerService;
    private final SceneManager sceneManager;

    public CustomerFXController(CustomerService customerService, SceneManager sceneManager) {
        this.customerService = customerService;
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize() {
        firstNameColumn.setCellValueFactory(data -> data.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(data -> data.getValue().lastNameProperty());
        emailColumn.setCellValueFactory(data -> data.getValue().emailProperty());
        addressColumn.setCellValueFactory(data -> data.getValue().addressProperty());
        phoneNumberColumn.setCellValueFactory(data -> data.getValue().phoneNumberProperty());

        // Ajouter les colonnes Modifier et Supprimer
        addEditColumn();
        addDeleteColumn();

        refreshButton.setOnAction(event -> loadCustomers());
        addButton.setOnAction(event -> addCustomer());
        searchButton.setOnAction(event -> searchCustomers());
        backButton.setOnAction(event -> sceneManager.switchScene(SceneType.DASHBOARD));

        loadCustomers();
    }

    private void loadCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        customerTable.getItems().setAll(customers);
        resetForm();
    }

    private void addCustomer() {
        if (!areFieldsValid()) {
            return;
        }

        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName(firstNameField.getText());
        customer.setLastName(lastNameField.getText());
        customer.setEmail(emailField.getText());
        customer.setAddress(addressField.getText());
        customer.setPhoneNumber(phoneNumberField.getText());

        customerService.createCustomer(customer);
        loadCustomers();
    }

    private boolean areFieldsValid() {
        // Vérification des champs obligatoires
        if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty()
                || emailField.getText().isEmpty() || addressField.getText().isEmpty()
                || phoneNumberField.getText().isEmpty()) {
            showAlert("Erreur", "Tous les champs doivent être remplis.");
            return false;
        }

        // Vérification du numéro de téléphone
        String phone = phoneNumberField.getText();
        if (!phone.matches("\\d{10}")) {
            showAlert("Erreur", "Le numéro de téléphone est incomplet");
            return false;
        }

        // Vérification de l'adresse email
        String email = emailField.getText();
        if (!email.contains("@")) {
            showAlert("Erreur", "Le format de l'adresse mail est invalide.");
            return false;
        }

        return true;
    }

    private void addEditColumn() {
        editColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Modifier");

            {
                editButton.setOnAction(event -> {
                    CustomerDTO customer = getTableView().getItems().get(getIndex());
                    handleEditCustomer(customer);
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
                    CustomerDTO customer = getTableView().getItems().get(getIndex());
                    handleDeleteCustomer(customer);
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

    private void handleEditCustomer(CustomerDTO customer) {
        firstNameField.setText(customer.getFirstName());
        lastNameField.setText(customer.getLastName());
        emailField.setText(customer.getEmail());
        addressField.setText(customer.getAddress());
        phoneNumberField.setText(customer.getPhoneNumber());

        addButton.setText("Modifier");
        addButton.setOnAction(event -> updateCustomer(customer));
    }

    private void updateCustomer(CustomerDTO customer) {
        if (!areFieldsValid()) {
            return;
        }

        customer.setFirstName(firstNameField.getText());
        customer.setLastName(lastNameField.getText());
        customer.setEmail(emailField.getText());
        customer.setAddress(addressField.getText());
        customer.setPhoneNumber(phoneNumberField.getText());

        customerService.updateCustomer(customer.getId(), customer);
        loadCustomers();
    }

    private void handleDeleteCustomer(CustomerDTO customer) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous vraiment supprimer ce client ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                customerService.deleteCustomer(customer.getId());
                loadCustomers();
            }
        });
    }

    private void searchCustomers() {
        String query = searchField.getText().trim().toLowerCase();
        if (query.isEmpty()) {
            loadCustomers();
            return;
        }

        List<CustomerDTO> customers = customerService.getAllCustomers().stream()
                .filter(c -> c.getFirstName().toLowerCase().contains(query) ||
                        c.getLastName().toLowerCase().contains(query))
                .collect(Collectors.toList());
        customerTable.getItems().setAll(customers);
    }

    private void resetForm() {
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        addressField.clear();
        phoneNumberField.clear();
        searchField.clear();
        addButton.setText("Ajouter");
        addButton.setOnAction(event -> addCustomer());
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
