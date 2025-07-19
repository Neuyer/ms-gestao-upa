package com.fiap.upa.core.dto;

import com.fiap.upa.core.entity.SaoPauloCity;

public record CreateAddressDTO(
        String street,
        SaoPauloCity city,
        String state,
        String zipCode,
        Integer number
) {
}
