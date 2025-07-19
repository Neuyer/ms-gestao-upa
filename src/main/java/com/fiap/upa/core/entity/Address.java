package com.fiap.upa.core.entity;

import java.util.Objects;
import java.util.UUID;

public class Address {

    private final UUID id;
    private final String street;
    private final SaoPauloCity city;
    private final String state;
    private final String zipCode;
    private final Integer number;

    private Address(UUID id, String street, SaoPauloCity city, String state, String zipCode, Integer number) {
        this.id = Objects.requireNonNull(id, "ID cannot be null");
        this.street = Objects.requireNonNull(street, "Street cannot be null");
        this.city = Objects.requireNonNull(city, "City cannot be null");
        this.state = Objects.requireNonNull(state, "State cannot be null");
        this.zipCode = Objects.requireNonNull(zipCode, "Zip code cannot be null");
        this.number = Objects.requireNonNull(number, "Zip code cannot be null");
    }

    public static Address createNewAddress(String street, SaoPauloCity city, String state, String zipCode, Integer number) {
        return new Address(UUID.randomUUID(), street, city, state, zipCode, number);
    }

    public static Address loadAddress(UUID id, String street, SaoPauloCity city, String state, String zipCode, Integer number) {
        return new Address(id, street, city, state, zipCode, number);
    }

    public UUID getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public SaoPauloCity getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return id.equals(address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }


}

