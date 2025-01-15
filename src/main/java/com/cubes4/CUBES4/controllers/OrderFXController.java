package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.annotation.FXMLController;
import com.cubes4.CUBES4.dto.OrderDTO;
import com.cubes4.CUBES4.services.OrderService;
import com.cubes4.CUBES4.util.SceneManager;
import com.cubes4.CUBES4.util.SceneType;
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
    private TableColumn<OrderDTO, String> productColumn;

    @FXML
    private TableColumn<OrderDTO, Integer> quantityColumn;

    @FXML
    private TableColumn<OrderDTO, Double> priceColumn;

    @FXML
    private TableColumn<OrderDTO, String> paymentStatusColumn;

    @FXML
    private Button refreshButton;

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
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        supplierOrderColumn.setCellValueFactory(new PropertyValueFactory<>("orderType"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        productColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        paymentStatusColumn.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));

        refreshButton.setOnAction(event -> loadOrders());
        backButton.setOnAction(event -> sceneManager.loadView(SceneType.DASHBOARD));

        loadOrders();
    }

    private void loadOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        orderTable.getItems().setAll(orders);
    }
}
