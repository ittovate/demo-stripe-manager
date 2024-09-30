package com.example.stripemanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDTO {


    @JsonProperty("active")
    private Boolean active;

    @JsonProperty("description")
    private String description;

    @JsonProperty("name")
    private String name;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "active=" + active +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
