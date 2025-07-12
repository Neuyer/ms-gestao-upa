package com.fiap.upa.infrastructure.repository.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Builder
@Document("attendants")
public class AttendantModel {
    @Id
    private UUID id;
    private String name;
    private String document;
}
