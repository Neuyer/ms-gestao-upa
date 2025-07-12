package com.fiap.upa.infrastructure.repository.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@Document("upas")
public class UPAModel {
    private UUID id;
    private List<DoctorModel> doctors;
    private List<AttendantModel> attendants;
    private String nickName;
    private AddressModel address;
}
