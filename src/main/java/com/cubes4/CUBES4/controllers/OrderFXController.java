package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.annotation.FXMLController;
import com.cubes4.CUBES4.dto.OrderDTO;
import com.cubes4.CUBES4.dto.OrderLineDTO;
import com.cubes4.CUBES4.services.CustomerService;
import com.cubes4.CUBES4.services.OrderService;
import com.cubes4.CUBES4.services.SupplierService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;
import java.util.List;

@FXMLController
public class OrderFXController {

    @FXML
    private TableView<OrderDTO> orderTable;
    @FXML
    private TableColumn<OrderDTO, String> orderDateColumn, statusColumn,
            isSupplierOrderColumn, customerOrSupplierNameColumn;

    @FXML
    private TableColumn<OrderDTO, Double> totalPriceColumn;

    @FXML
    private TableColumn<OrderDTO, Void> editColumn, deleteColumn;

    @FXML
    private TableView<OrderLineDTO> orderLineTable;

    @FXML
    private TableColumn<OrderLineDTO, String> productColumn;

    @FXML
    private TableColumn<OrderLineDTO, Integer> quantityColumn;

    @FXML
    private TableColumn<OrderLineDTO, Double> priceColumn;

    @FXML
    private TableColumn<OrderLineDTO, Void> modifyQuantityColumn;

    @FXML
    private ComboBox<String> articleComboBox, statusComboBox, typeComboBox, nameComboBox;

    @FXML
    private TextField quantityField;

    @FXML
    private Button refreshButton, addOrderButton, addLineButton;

    private final OrderService orderService;
    private final CustomerService customerService;
    private final SupplierService supplierService;

    private OrderDTO currentOrder;

    public OrderFXController(OrderService orderService, CustomerService customerService, SupplierService supplierService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.supplierService = supplierService;
    }

    @FXML
    public void initialize() {
        configureOrderTableColumns();
        configureOrderLineTableColumns();
        setupDynamicButtons();
        initializeComboBoxes();

        addOrderButton.setOnAction(event -> handleAddOrder());
        addLineButton.setOnAction(event -> handleAddLine());
        refreshButton.setOnAction(event -> loadOrders());

        loadOrders();
        clearForm();

        orderTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                currentOrder = newValue;
                loadOrderLines(newValue.getItems());
                loadOrderDetailsInForm(newValue);
            }
        });
    }

    private void configureOrderTableColumns() {
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        isSupplierOrderColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().isSupplierOrder() ? "Fournisseur" : "Client"));
        customerOrSupplierNameColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().isSupplierOrder() ?
                        data.getValue().getSupplierName() : data.getValue().getCustomerName()));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    }

    private void configureOrderLineTableColumns() {
        productColumn.setCellValueFactory(new PropertyValueFactory<>("articleName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
    }

    private void setupDynamicButtons() {
        setupLineModifyButtons();
        setupActionButtons();
    }

    private void initializeComboBoxes() {
        statusComboBox.setItems(FXCollections.observableArrayList("PENDING", "CONFIRMED", "SHIPPED", "DELIVERED", "CANCELLED"));
        typeComboBox.setItems(FXCollections.observableArrayList("Client", "Fournisseur"));
        typeComboBox.setOnAction(event -> updateNameComboBox());
        loadArticlesIntoComboBox();
    }

    private void updateNameComboBox() {
        if ("Client".equals(typeComboBox.getValue())) {
            nameComboBox.setItems(FXCollections.observableArrayList(customerService.getAllCustomerNames()));
        } else if ("Fournisseur".equals(typeComboBox.getValue())) {
            nameComboBox.setItems(FXCollections.observableArrayList(supplierService.getAllSupplierNames()));
        }
    }

    private void loadArticlesIntoComboBox() {
        articleComboBox.setItems(FXCollections.observableArrayList(orderService.getAllArticleNames()));
    }

    private void loadOrders() {
        List<OrderDTO> orders = orderService.getAllOrdersWithNames();
        orderTable.setItems(FXCollections.observableArrayList(orders));

        loadArticlesIntoComboBox();
        updateNameComboBox();
    }

    private void loadOrderLines(List<OrderLineDTO> items) {
        orderLineTable.setItems(FXCollections.observableArrayList(items));
    }

    private void loadOrderDetailsInForm(OrderDTO order) {
        if (order != null) {
            statusComboBox.setValue(order.getStatus());
            typeComboBox.setValue(order.isSupplierOrder() ? "Fournisseur" : "Client");
            nameComboBox.setValue(order.isSupplierOrder() ? order.getSupplierName() : order.getCustomerName());
        }
    }

    private void handleAddOrder() {
        try {
            // Validation des champs et ajout de logs
            if (statusComboBox.getValue() == null) {
                System.out.println("Statut non sélectionné !");
                showAlert("Veuillez sélectionner un statut !");
                return;
            }
            if (typeComboBox.getValue() == null) {
                System.out.println("Type non sélectionné !");
                showAlert("Veuillez sélectionner le type (Client ou Fournisseur) !");
                return;
            }
            if (nameComboBox.getValue() == null || nameComboBox.getValue().trim().isEmpty()) {
                System.out.println("Nom du Client ou Fournisseur non sélectionné !");
                showAlert("Veuillez sélectionner un nom pour le client ou le fournisseur !");
                return;
            }

            // Logs avant création
            System.out.println("Statut : " + statusComboBox.getValue());
            System.out.println("Type : " + typeComboBox.getValue());
            System.out.println("Nom : " + nameComboBox.getValue());

            // Création de l'objet DTO
            OrderDTO newOrder = new OrderDTO();

            // Génération de la date actuelle avec l'heure
            String currentDateTime = java.time.LocalDateTime.now().toString().replace("T", " ");
            newOrder.setOrderDate(currentDateTime); // Format correct pour Timestamp.valueOf

            newOrder.setStatus(statusComboBox.getValue());

            // Attribution du type (Client/Fournisseur) et récupération de l'ID
            if ("Client".equals(typeComboBox.getValue())) {
                // Récupération du prénom et du nom
                String[] fullName = nameComboBox.getValue().split(" ", 2); // Sépare prénom et nom
                if (fullName.length < 2) {
                    System.out.println("Nom incomplet fourni : " + nameComboBox.getValue());
                    showAlert("Veuillez fournir un prénom et un nom pour le client !");
                    return;
                }
                // Récupération de l'ID du client
                Long customerId = customerService.getCustomerIdByFullName(fullName[0], fullName[1]);
                if (customerId == null) {
                    System.out.println("Client introuvable pour le nom : " + nameComboBox.getValue());
                    showAlert("Client introuvable !");
                    return;
                }
                newOrder.setCustomerId(customerId);
                newOrder.setSupplierOrder(false);
                System.out.println("Type : Client, ID : " + customerId);
            } else if ("Fournisseur".equals(typeComboBox.getValue())) {
                Long supplierId = supplierService.getSupplierIdByName(nameComboBox.getValue());
                if (supplierId == null) {
                    System.out.println("Fournisseur introuvable pour le nom : " + nameComboBox.getValue());
                    showAlert("Fournisseur introuvable !");
                    return;
                }
                newOrder.setSupplierId(supplierId);
                newOrder.setSupplierOrder(true);
                System.out.println("Type : Fournisseur, ID : " + supplierId);
            }

            // Appel au service et log
            System.out.println("Envoi de la commande au service...");
            orderService.createOrder(newOrder);
            System.out.println("Commande créée avec succès dans le service !");

            // Rafraîchissement et notification
            loadOrders();
            clearForm();
            showAlert("Commande ajoutée avec succès !");
        } catch (Exception e) {
            // Logs en cas d'erreur
            System.out.println("Erreur lors de la création de la commande : " + e.getMessage());
            e.printStackTrace();
            showAlert("Erreur lors de l'ajout de la commande : " + e.getMessage());
        }
    }

    private void validateOrderForm() {
        if (statusComboBox.getValue() == null) {
            throw new IllegalArgumentException("Veuillez sélectionner un statut !");
        }
        if (typeComboBox.getValue() == null) {
            throw new IllegalArgumentException("Veuillez sélectionner le type (Client ou Fournisseur) !");
        }
        if (nameComboBox.getValue() == null) {
            throw new IllegalArgumentException("Veuillez sélectionner un nom pour le client ou le fournisseur !");
        }
    }

    private OrderDTO prepareOrderDTO() {
        OrderDTO newOrder = new OrderDTO();
        newOrder.setOrderDate(LocalDateTime.now().toString().replace("T", " "));
        newOrder.setStatus(statusComboBox.getValue());

        if ("Client".equals(typeComboBox.getValue())) {
            // Récupération du prénom et du nom
            String[] fullName = nameComboBox.getValue().split(" ", 2); // Sépare prénom et nom
            if (fullName.length < 2) {
                throw new IllegalArgumentException("Veuillez fournir un prénom et un nom pour le client !");
            }
            Long customerId = customerService.getCustomerIdByFullName(fullName[0], fullName[1]);
            if (customerId == null) {
                throw new IllegalArgumentException("Client introuvable !");
            }
            newOrder.setCustomerId(customerId);
            newOrder.setSupplierOrder(false);
        } else if ("Fournisseur".equals(typeComboBox.getValue())) {
            Long supplierId = supplierService.getSupplierIdByName(nameComboBox.getValue());
            if (supplierId == null) {
                throw new IllegalArgumentException("Fournisseur introuvable !");
            }
            newOrder.setSupplierId(supplierId);
            newOrder.setSupplierOrder(true);
        }

        return newOrder;
    }


    private void handleAddLine() {
        if (currentOrder != null) {
            try {
                OrderLineDTO line = new OrderLineDTO();
                line.setArticleName(articleComboBox.getValue());
                line.setQuantity(Integer.parseInt(quantityField.getText()));
                orderService.addOrderLine(currentOrder.getId(), line);
                loadOrders();
            } catch (Exception e) {
                showAlert("Erreur lors de l'ajout de la ligne : " + e.getMessage());
            }
        } else {
            showAlert("Veuillez sélectionner une commande.");
        }
    }

    private void setupLineModifyButtons() {
        modifyQuantityColumn.setCellFactory(param -> new TableCell<>() {
            private final Button modifyButton = new Button("Modifier");

            {
                modifyButton.setOnAction(event -> {
                    OrderLineDTO line = getTableView().getItems().get(getIndex());
                    handleModifyLine(line);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(modifyButton);
                }
            }
        });
    }

    private void setupActionButtons() {
        editColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Modifier");

            {
                editButton.setOnAction(event -> {
                    OrderDTO order = getTableView().getItems().get(getIndex());
                    loadOrderDetailsInForm(order);
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

        deleteColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Supprimer");

            {
                deleteButton.setOnAction(event -> {
                    OrderDTO order = getTableView().getItems().get(getIndex());
                    handleDeleteOrder(order);
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

    private void handleDeleteOrder(OrderDTO order) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirmez la suppression de la commande ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                try {
                    orderService.deleteOrder(order.getId());
                    loadOrders();
                } catch (Exception e) {
                    showAlert("Erreur lors de la suppression : " + e.getMessage());
                }
            }
        });
    }

    private void handleModifyLine(OrderLineDTO line) {
        TextInputDialog dialog = new TextInputDialog(String.valueOf(line.getQuantity()));
        dialog.setTitle("Modifier la Quantité");
        dialog.setHeaderText("Modifier la quantité de l'article : " + line.getArticleName());
        dialog.setContentText("Nouvelle quantité :");

        dialog.showAndWait().ifPresent(newQuantity -> {
            try {
                line.setQuantity(Integer.parseInt(newQuantity));
                orderService.updateOrderLine(currentOrder.getId(), line);
                loadOrders();
            } catch (NumberFormatException e) {
                showAlert("Quantité invalide !");
            } catch (Exception e) {
                showAlert("Erreur lors de la mise à jour : " + e.getMessage());
            }
        });
    }

    private void clearForm() {
        currentOrder = null;
        statusComboBox.setValue(null);
        typeComboBox.setValue(null);
        nameComboBox.setValue(null);
        articleComboBox.setValue(null);
        quantityField.clear();
        orderLineTable.getItems().clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING, message, ButtonType.OK);
        alert.showAndWait();
    }
}
