package com.fiap.upa.core.dto;

public record CreateDoctorDTO(
        String name,
        String document,
        String specialty
) {
}
