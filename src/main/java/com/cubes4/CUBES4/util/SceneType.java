package com.cubes4.CUBES4.util;

public enum SceneType {
    LOGIN("/fxml/login.fxml", "Connexion"), // Nouvelle entrée
    DASHBOARD("/fxml/dashboard.fxml", "Dashboard"),
    MANAGE_ARTICLES("/fxml/articles.fxml", "Gérer les Articles"),
    MANAGE_CUSTOMERS("/fxml/customers.fxml", "Gérer les Clients"),
    MANAGE_ORDERS("/fxml/orders.fxml", "Gérer les Commandes"),
    MANAGE_SUPPLIERS("/fxml/suppliers.fxml", "Gérer les Fournisseurs"),
    MANAGE_FAMILIES("/fxml/families.fxml", "Gérer les Familles"),
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
