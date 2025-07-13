package com.fiap.upa.core.dto;

import com.fiap.upa.core.entity.Urgency;

import java.util.Objects;
import java.util.UUID;

public record CreateReceptionDTO(
        String patientName,
        String patientDocument,
        String specialty,
        UUID doctorId,
        UUID attendantId,
        Urgency urgencyLevel
) {

    public CreateReceptionDTO(String patientName, String patientDocument, String specialty, UUID doctorId, UUID attendantId, Urgency urgencyLevel) {
        this.patientName = patientName;
        this.patientDocument = patientDocument;
        this.specialty = specialty;
        this.doctorId = Objects.requireNonNull(doctorId, "doctorID cannot be null");
        this.attendantId = Objects.requireNonNull(attendantId, "attendantId cannot be null");
        this.urgencyLevel = urgencyLevel;
    }
}
