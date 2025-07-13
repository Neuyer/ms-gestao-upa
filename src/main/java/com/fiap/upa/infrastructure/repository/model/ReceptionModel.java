package com.fiap.upa.infrastructure.repository.model;

import com.fiap.upa.core.entity.ServiceStatus;
import com.fiap.upa.core.entity.Urgency;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@Document("receptions")
public class ReceptionModel {
    @Id
    private UUID id;
    private String patientName;
    private String patientDocument;
    private String serviceNumber;
    private String specialty;
    private UPAModel upa;
    private DoctorModel doctor;
    private AttendantModel attendant;
    private Urgency urgencyLevel;
    private ServiceStatus status;
    private LocalDateTime creationDate;
    private LocalDateTime endDate;
}
