package com.example.trabalho_mobile.entity;

import java.util.UUID;

public class Category {
    private UUID id;
    private String name;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Category create(UUID id, String name) {
        return new Category(id, name);
    }

    private Category(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
