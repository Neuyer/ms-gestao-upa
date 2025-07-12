package com.fiap.upa.core.dto;

public record CreateAddressDTO(
        String street,
        String city,
        String state,
        String zipCode,
        Integer number
) {
}
