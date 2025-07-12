package com.fiap.upa.infrastructure.repository.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AddressModel {
    UUID id;
    String street;
    String city;
    String state;
    String zipCode;
    Integer number;
}
