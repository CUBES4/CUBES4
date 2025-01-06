package com.cubes4.CUBES4.util;

public enum SceneType {
    DASHBOARD("/fxml/dashboard.fxml", "Dashboard"),
    MANAGE_ARTICLES("/fxml/articles.fxml", "Manage Articles"),
    MANAGE_CUSTOMERS("/fxml/customers.fxml", "Manage Customers"),
    MANAGE_ORDERS("/fxml/orders.fxml", "Manage Orders"),
    MANAGE_SUPPLIERS("/fxml/suppliers.fxml", "Gérer les Fournisseurs"),
    SETTINGS("/fxml/settings.fxml", "Paramètres");

    private final String fxmlPath;
    private final String title;

    SceneType(String fxmlPath, String title) {
        this.fxmlPath = fxmlPath;
        this.title = title;
    }

    public String getFxmlPath() {
        return fxmlPath;
    }

    public String getTitle() {
        return title;
    }
}
