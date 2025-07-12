package com.fiap.upa.infrastructure.repository.model;

import com.fiap.upa.core.entity.DoctorSchedule;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Builder
@Document("doctors")
public class DoctorModel {
    @Id
    private UUID id;

    private String name;

    private String document;

    private String specialty;

    private boolean isAvailable;
    private DoctorSchedule doctorSchedule;
}
