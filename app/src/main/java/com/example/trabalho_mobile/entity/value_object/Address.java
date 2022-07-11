package com.example.trabalho_mobile.entity.value_object;

public class Address {
    private double lat;
    private double lon;
    private String value;

    public static Address create(double lat, double lon, String value) {
        return new Address(lat, lon, value);
    }

    public double getLat() {
        return this.lat;
    }

    public double getLon() {
        return this.lon;
    }

    public String getValue() {
        return this.value;
    }

    private Address(double lat, double lon, String value) {
        this.lat = lat;
        this.lon = lon;
        this.value = value;
    }
}
