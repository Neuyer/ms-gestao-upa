package com.fiap.upa.core.dto;

import java.time.LocalDateTime;

public record CreateWorkShiftDTO(
        LocalDateTime start,
        LocalDateTime end
) {
}
