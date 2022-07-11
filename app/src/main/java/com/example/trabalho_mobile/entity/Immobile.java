package com.example.trabalho_mobile.entity;

import com.example.trabalho_mobile.entity.value_object.Address;

import java.util.UUID;

public class Immobile {
    private UUID id;
    private String title;
    private String description;
    private Address address;
    private String photo;
    private Category category;

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoto() {
        return photo;
    }

    public Category getCategory() {
        return category;
    }

    public static Immobile create(UUID id, String title, String description, Address address, Category category) {
        return new Immobile(id, title, description, address, category);
    }

    private Immobile(UUID id, String title, String description, Address address, Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.address = address;
        this.category = category;
    }
}
