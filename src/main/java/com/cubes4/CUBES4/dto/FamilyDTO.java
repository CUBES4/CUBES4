package com.cubes4.CUBES4.dto;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class FamilyDTO {

    private final SimpleLongProperty id;
    private final SimpleStringProperty name;

    public FamilyDTO() {
        this.id = new SimpleLongProperty();
        this.name = new SimpleStringProperty();
    }

    public FamilyDTO(long id, String name) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
    }

    public long getId() {
        return id.get();
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }
}
