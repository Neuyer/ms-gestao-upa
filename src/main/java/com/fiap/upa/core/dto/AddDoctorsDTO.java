package com.fiap.upa.core.dto;

import java.util.List;
import java.util.UUID;

public record AddDoctorsDTO(
        UUID upaId,
        List<UUID> doctorsIds
        ) {
}
