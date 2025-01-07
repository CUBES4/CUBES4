package com.cubes4.CUBES4.dto;

import javafx.beans.property.*;

public class ArticleDTO {

    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final DoubleProperty unitPrice = new SimpleDoubleProperty();
    private final IntegerProperty stock = new SimpleIntegerProperty();
    private final IntegerProperty stockMin = new SimpleIntegerProperty();
    private final LongProperty familyId = new SimpleLongProperty();

    public LongProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public DoubleProperty unitPriceProperty() {
        return unitPrice;
    }

    public IntegerProperty stockProperty() {
        return stock;
    }

    public IntegerProperty stockMinProperty() {
        return stockMin;
    }

    public LongProperty familyIdProperty() {
        return familyId;
    }

    public long getId() {
        return id.get();
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public double getUnitPrice() {
        return unitPrice.get();
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice.set(unitPrice);
    }

    public int getStock() {
        return stock.get();
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }

    public int getStockMin() {
        return stockMin.get();
    }

    public void setStockMin(int stockMin) {
        this.stockMin.set(stockMin);
    }

    public Long getFamilyId() {
        return familyId.get() == 0 ? null : familyId.get();
    }

    public void setFamilyId(Long familyId) {
        this.familyId.set(familyId == null ? 0 : familyId);
    }
}
