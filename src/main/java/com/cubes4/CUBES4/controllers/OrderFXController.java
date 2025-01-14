package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.annotation.FXMLController;
import com.cubes4.CUBES4.dto.OrderDTO;
import com.cubes4.CUBES4.dto.OrderLineDTO;
import com.cubes4.CUBES4.services.CustomerService;
import com.cubes4.CUBES4.services.OrderService;
import com.cubes4.CUBES4.services.SupplierService;
import com.cubes4.CUBES4.util.SceneManager;
import com.cubes4.CUBES4.util.SceneType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

@FXMLController
public class OrderFXController {

    // Table principale des commandes
    @FXML private TableView<OrderDTO> orderTable;
    @FXML private TableColumn<OrderDTO, String> orderDateColumn;
    @FXML private TableColumn<OrderDTO, String> statusColumn;
    @FXML private TableColumn<OrderDTO, Double> totalPriceColumn;

    // Sous-table pour les lignes de commandes
    @FXML private TableView<OrderLineDTO> orderLineTable;
    @FXML private TableColumn<OrderLineDTO, String> productColumn;
    @FXML private TableColumn<OrderLineDTO, Integer> quantityColumn;
    @FXML private TableColumn<OrderLineDTO, Double> priceColumn;

    // Champs de formulaire
    @FXML private ComboBox<String> customerComboBox;
    @FXML private ComboBox<String> supplierComboBox;

    // Boutons
    @FXML private Button refreshButton;
    @FXML private Button backButton;

    private final OrderService orderService;
    private final CustomerService customerService;
    private final SupplierService supplierService;
    private final SceneManager sceneManager;

    public OrderFXController(OrderService orderService, CustomerService customerService, SupplierService supplierService, SceneManager sceneManager) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.supplierService = supplierService;
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize() {
        // Configurer les colonnes pour les commandes
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        // Configurer les colonnes pour les lignes de commande
        productColumn.setCellValueFactory(new PropertyValueFactory<>("articleName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        // Peupler les ComboBox
        customerComboBox.setItems(FXCollections.observableArrayList(customerService.getAllCustomerNames()));
        supplierComboBox.setItems(FXCollections.observableArrayList(supplierService.getAllSupplierNames()));

        // Configurer les actions des boutons
        refreshButton.setOnAction(event -> loadOrders());
        backButton.setOnAction(event -> sceneManager.switchScene(SceneType.DASHBOARD));

        // Charger les données initiales
        loadOrders();
    }

    /**
     * Charge toutes les commandes et les affiche dans le tableau principal.
     */
    private void loadOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        orderTable.setItems(FXCollections.observableArrayList(orders));

        // Ajouter un écouteur pour charger les lignes de commandes d'une commande sélectionnée
        orderTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                loadOrderLines(newValue);
            }
        });
    }

    /**
     * Charge les lignes d'une commande sélectionnée et les affiche dans la sous-table.
     *
     * @param selectedOrder La commande sélectionnée
     */
    private void loadOrderLines(OrderDTO selectedOrder) {
        if (selectedOrder != null && selectedOrder.getItems() != null) {
            orderLineTable.setItems(FXCollections.observableArrayList(selectedOrder.getItems()));
        } else {
            orderLineTable.setItems(FXCollections.observableArrayList());
        }
    }
}
