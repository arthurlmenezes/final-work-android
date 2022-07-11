package com.example.trabalho_mobile.entity;

import java.util.List;
import java.util.UUID;

public class User {
    private UUID id;
    private String name;
    private String email;
    private String phoneNumber;
    private List<Immobile> immobileList;
    private List<Ad> adList;
    private List<Ad> favoriteAdList;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Ad> getAdList() {
        return adList;
    }

    public void addAd(Ad ad) {
        this.adList.add(ad);
    }

    public void removeAd(Ad ad) {
        this.adList.remove(ad);
    }

    public void addFavoriteAdList(Ad ad) {
        this.favoriteAdList.add(ad);
    }

    public void removeFavoriteAdList(Ad ad) {
        this.favoriteAdList.remove(ad);
    }

    public void addImmobileList(Immobile immobile) {
        this.immobileList.add(immobile);
    }

    public void removeImmobileList(Immobile immobile) {
        this.immobileList.remove(immobile);
    }

    public static User create(UUID id, String name, String email, String phoneNumber, List<Ad> adList, List<Ad> favoriteAdList) {
        return new User(id, name, email, phoneNumber, adList, favoriteAdList);
    }

    private User(UUID id, String name, String email, String phoneNumber, List<Ad> adList, List<Ad> favoriteAdList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.adList = adList;
        this.favoriteAdList = favoriteAdList;
    }
}
