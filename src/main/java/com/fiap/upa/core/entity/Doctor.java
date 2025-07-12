package com.fiap.upa.core.entity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Doctor {

    private final UUID id; // Identity, immutable after creation
    private String name;
    private Document document; // Value Object for document details
    private String specialty; // Value Object for specialty
    private boolean isAvailable; // Value Object/Enum for availability
    private DoctorSchedule schedule; // Value Object for work shift

    // Private constructor to enforce creation through factory methods
    private Doctor(UUID id, String name, Document document, String specialty,
                   Boolean isAvailable, DoctorSchedule schedule) {
        this.id = Objects.requireNonNull(id, "Doctor ID cannot be null");
        this.name = Objects.requireNonNull(name, "Doctor name cannot be null");
        this.document = Objects.requireNonNull(document, "Doctor document cannot be null");
        this.specialty = Objects.requireNonNull(specialty, "Doctor specialty cannot be null");
        this.isAvailable = Objects.requireNonNull(isAvailable, "Doctor availability status cannot be null");
        this.schedule = schedule;

        // Enforce invariants during creation
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Doctor name cannot be empty.");
        }
    }

    // Factory method to create a new Doctor
    public static Doctor createNewDoctor(String name, String documentNumber, String specialtyName, DoctorSchedule schedule) {
        return new Doctor(
                UUID.randomUUID(),
                name,
                new CpfDocument(documentNumber), // Create Document value object
                specialtyName.toUpperCase(), // Create Specialty value object
                false,
                schedule

        );
    }

    // Factory method to load an existing Doctor from persistence
    public static Doctor loadDoctor(UUID id, String name, String documentNumber, String specialtyName,
                                    boolean availabilityStatus, DoctorSchedule schedule) {
        return new Doctor(
                id,
                name,
                new CpfDocument(documentNumber),
                specialtyName,
                availabilityStatus,
                schedule
        );
    }

    // --- Getters ---
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Document getDocument() {
        return document;
    }

    public String getSpecialty() {
        return specialty;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public DoctorSchedule getSchedule() {
        return schedule;
    }

    // --- Behavior (Methods that encapsulate business rules) ---

    public void updateName(String newName) {
        if (newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException("Doctor name cannot be null or empty.");
        }
        this.name = newName;
    }

    public void updateSpecialty(String newSpecialtyName) {
        if (newSpecialtyName == null || newSpecialtyName.trim().isEmpty()) {
            throw new IllegalArgumentException("Specialty name cannot be null or empty.");
        }
        this.specialty = newSpecialtyName.toUpperCase();
    }

    public void updateScheduler(DoctorSchedule doctorSchedule) {
        if (doctorSchedule == null) {
            throw new IllegalArgumentException("doctorSchedule cannot be null or empty.");
        }
        this.schedule = doctorSchedule;
    }

    public void changeAvailability(boolean newStatus) {
        Objects.requireNonNull(newStatus, "Availability status cannot be null.");
        if (this.isAvailable != newStatus) {
            this.isAvailable = newStatus;
        }
    }

}

