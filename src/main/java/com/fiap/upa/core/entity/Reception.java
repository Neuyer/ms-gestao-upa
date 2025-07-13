package com.fiap.upa.core.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Reception {
    private UUID id;
    private String patientName;
    private String patientDocument;
    private String serviceNumber;
    private String specialty;
    private UPA upa;
    private Doctor doctor;
    private Attendant attendant;
    private Urgency urgencyLevel;
    private ServiceStatus status;
    private LocalDateTime creationDate;
    private LocalDateTime endDate;

    private Reception(UUID id, String patientName, String patientDocument, String serviceNumber, String specialty, UPA upa, Doctor doctor, Attendant attendant, Urgency urgencyLevel, ServiceStatus status, LocalDateTime creationDate, LocalDateTime endDate) {
        this.id = Objects.requireNonNull(id, "Reception id must not be null");
        this.patientName = Objects.requireNonNull(patientName, "Reception patientName must not be null");
        this.patientDocument = Objects.requireNonNull(patientDocument, "Reception patientDocument must not be null");
        this.serviceNumber = Objects.requireNonNull(serviceNumber, "Reception serviceNumber must not be null");
        this.specialty = Objects.requireNonNull(specialty, "Reception specialty must not be null");
        this.upa = Objects.requireNonNull(upa, "Reception UPA must not be null");
        this.doctor = Objects.requireNonNull(doctor, "Reception doctor must not be null");
        this.attendant = Objects.requireNonNull(attendant, "Reception attendant must not be null");
        this.urgencyLevel = Objects.requireNonNull(urgencyLevel, "Reception urgencyLevel must not be null");
        this.status = Objects.requireNonNull(status, "Reception status must not be null");
        this.creationDate = Objects.requireNonNull(creationDate, "Reception creationDate must not be null");
        this.endDate = endDate;
    }

    private Reception(UUID id, String patientName, String patientDocument, String serviceNumber, String specialty, Urgency urgencyLevel, ServiceStatus status, LocalDateTime creationDate, LocalDateTime endDate) {
        this.id = Objects.requireNonNull(id, "Reception id must not be null");
        this.patientName = Objects.requireNonNull(patientName, "Reception patientName must not be null");
        this.patientDocument = Objects.requireNonNull(patientDocument, "Reception patientDocument must not be null");
        this.serviceNumber = Objects.requireNonNull(serviceNumber, "Reception serviceNumber must not be null");
        this.specialty = Objects.requireNonNull(specialty, "Reception specialty must not be null");
        this.urgencyLevel = Objects.requireNonNull(urgencyLevel, "Reception urgencyLevel must not be null");
        this.status = Objects.requireNonNull(status, "Reception status must not be null");
        this.creationDate = Objects.requireNonNull(creationDate, "Reception creationDate must not be null");
        this.endDate = endDate;
    }

    static public Reception createNewReception(String patientName, String patientDocument, String serviceNumber, String specialty, Urgency urgencyLevel) {
        return new Reception(
                UUID.randomUUID(),
                patientName,
                patientDocument,
                serviceNumber,
                specialty,
                urgencyLevel,
                ServiceStatus.WAITING,
                LocalDateTime.now(),
                null
        );
    }

    static public Reception loadReception(UUID id, String patientName, String patientDocument, String serviceNumber, String specialty, UPA upa, Doctor doctor, Attendant attendant, Urgency urgencyLevel, ServiceStatus status, LocalDateTime creationDate, LocalDateTime endDate) {
        return new Reception(
                id,
                patientName,
                patientDocument,
                serviceNumber,
                specialty,
                upa,
                doctor,
                attendant,
                urgencyLevel,
                status,
                creationDate,
                endDate
        );
    }

    public void updateUpa(UPA upa) {
        this.upa = Objects.requireNonNull(upa, "Reception UPA must not be null");
    }

    public void updateDoctor(Doctor doctor) {
        this.doctor = Objects.requireNonNull(doctor, "Reception doctor must not be null");
    }

    public void updateAttendant(Attendant attendant) {
        this.attendant = Objects.requireNonNull(attendant, "Reception attendant must not be null");
    }

    public void startService() {
        if (ServiceStatus.WAITING.equals(this.status))
            this.status = ServiceStatus.ONGOING;
        else
            throw new IllegalArgumentException("Atendimento ja finalizado ou em andamento");
    }

    public void endService() {
        if (ServiceStatus.FINISHED.equals(this.status))
            throw new IllegalArgumentException("Atendimento ja finalizado");
        else {
            this.endDate = LocalDateTime.now();
            this.status = ServiceStatus.FINISHED;
        }
    }

    public UUID getId() {
        return id;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPatientDocument() {
        return patientDocument;
    }

    public String getServiceNumber() {
        return serviceNumber;
    }

    public String getSpecialty() {
        return specialty;
    }

    public UPA getUpa() {
        return upa;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Attendant getAttendant() {
        return attendant;
    }

    public Urgency getUrgencyLevel() {
        return urgencyLevel;
    }

    public ServiceStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
