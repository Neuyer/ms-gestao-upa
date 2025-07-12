package com.fiap.upa.core.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Client {
    private final String id;
    private final String name;
    private final LocalDate birthDate;
    private final CpfDocument document;
    private final List<Address> addresses;

    public Client(String id, String name, LocalDate birthDate, String document, List<Address> addresses) {
        this.id = Objects.requireNonNull(id, "ID cannot be null");
        this.name = Objects.requireNonNull(name, "Name cannot be null");
        this.birthDate = Objects.requireNonNull(birthDate, "Birth Date cannot be null");
        this.document = new CpfDocument(document);
        this.addresses = Objects.isNull(addresses) ? List.of() : addresses;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public CpfDocument getDocument() {
        return document;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return id.equals(client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", document=" + document +
                ", addresses=" + addresses +
                '}';
    }
}
