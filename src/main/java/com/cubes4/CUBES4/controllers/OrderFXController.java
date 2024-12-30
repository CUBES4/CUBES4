package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.annotation.FXMLController;
import com.cubes4.CUBES4.dto.OrderDTO;
import com.cubes4.CUBES4.services.OrderService;
import com.cubes4.CUBES4.util.SceneManager;
import com.cubes4.CUBES4.util.SceneType;
import javafx.beans.property.ReadOnlyStringWrapper;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

@FXMLController
public class OrderFXController {

    @FXML
    private TableView<OrderDTO> orderTable;

    @FXML
    private TableColumn<OrderDTO, String> orderDateColumn;

    @FXML
    private TableColumn<OrderDTO, String> supplierOrderColumn;

    @FXML
    private TableColumn<OrderDTO, String> statusColumn;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button backButton;

    private final OrderService orderService;
    private final SceneManager sceneManager;

    public OrderFXController(OrderService orderService, SceneManager sceneManager) {
        this.orderService = orderService;
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize() {
        // Configure les colonnes du tableau
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        supplierOrderColumn.setCellValueFactory(data ->
                new ReadOnlyStringWrapper(data.getValue().isSupplierOrder() ? "Fournisseur" : "Client"));

        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Actions des boutons
        addButton.setOnAction(event -> handleAddOrder());
        deleteButton.setOnAction(event -> handleDeleteOrder());
        backButton.setOnAction(event -> sceneManager.switchScene(SceneType.DASHBOARD));

        // Charger les données des commandes
        loadOrders();
    }

    private void loadOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        orderTable.getItems().setAll(orders);
    }

    private void handleAddOrder() {
        // Ouvrir un formulaire pour ajouter une commande (non implémenté ici)
        System.out.println("Ajouter une commande (fonctionnalité à implémenter).");
    }

    private void handleDeleteOrder() {
        OrderDTO selectedOrder = orderTable.getSelectionModel().getSelectedItem();
        if (selectedOrder == null) {
            showAlert("Erreur", "Veuillez sélectionner une commande à supprimer.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous vraiment supprimer cette commande ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                orderService.deleteOrder(selectedOrder.getId());
                loadOrders();
            }
        });
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
