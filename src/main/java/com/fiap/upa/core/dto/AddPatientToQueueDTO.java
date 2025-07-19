package com.fiap.upa.core.dto;

import com.fiap.upa.core.entity.Urgency;

public record AddPatientToQueueDTO(
        PatientRequest patient, Urgency emergencyCategory
) {
    public record PatientRequest(String name, String queueNumber) {
    }
}
