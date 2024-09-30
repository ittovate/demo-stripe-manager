package com.example.stripemanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductResponseDTO {

    @JsonProperty("active")
    private Boolean active;

    @JsonProperty("livemode")
    private Boolean livemode;  // Assuming you want to include this field

    @JsonProperty("name")
    private String name;

    @JsonProperty("updated")
    private Long updated; // Change this to appropriate type based on actual value

    @JsonProperty("type")
    private String type;

    @JsonProperty("id")
    private  String id ;



    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getLivemode() {
        return livemode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLivemode(Boolean livemode) {
        this.livemode = livemode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
