package com.fiap.upa.core.dto;

import java.time.LocalDate;

public record CreateClientDTO(
        String name,
        LocalDate birthDate,
        String document
) {
}
