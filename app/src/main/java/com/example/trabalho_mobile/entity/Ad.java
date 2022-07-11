package com.example.trabalho_mobile.entity;

import java.util.UUID;

public class Ad {
    private UUID id;
    private Immobile immobile;
    private double price;

    public UUID getId() {
        return id;
    }

    public Immobile getImmobile() {
        return immobile;
    }

    public double getPrice() {
        return price;
    }

    public static Ad create(UUID id, Immobile immobile, double price) {
        return new Ad(id, immobile, price);
    }

    private Ad(UUID id, Immobile immobile, double price) {
        this.id = id;
        this.immobile = immobile;
        this.price = price;
    }
}
