package com.fiap.upa.core.entity;

import java.util.Objects;
import java.util.UUID;

public class Attendant {
    private UUID id;
    private String name;
    private Document document;

    private Attendant(UUID id, String name, String document) {
        this.id = Objects.requireNonNull(id, "Doctor ID cannot be null");
        this.name = Objects.requireNonNull(name, "Doctor name cannot be null");
        this.document = new CpfDocument(document);
    }


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Document getDocument() {
        return document;
    }

    static public Attendant createNewAttendant(String name, String document) {
        return new Attendant(
                UUID.randomUUID(),
                name,
                document);
    }

    static public Attendant loadAttendant(UUID id, String name, String document) {
        return new Attendant(id, name, document);
    }
}

