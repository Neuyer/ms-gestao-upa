package com.fiap.upa.core.dto;

import java.util.List;
import java.util.UUID;

public record AddAttendantsDTO(
        UUID upaId,
        List<UUID> attendantsIds
        ) {
}
