package com.fiap.upa.infrastructure.repository.model;

import com.fiap.upa.core.entity.Address;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Document("clients")
public class ClientModel {
    @Id
    private String id;
    private String name;
    private LocalDate birthDate;
    private String document;
    private List<Address> addresses;
}
