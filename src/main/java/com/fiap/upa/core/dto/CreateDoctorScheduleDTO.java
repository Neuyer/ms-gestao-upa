package com.fiap.upa.core.dto;

import java.util.List;
import java.util.UUID;

public record CreateDoctorScheduleDTO(
        UUID doctorId,
        List<CreateWorkShiftDTO> createWorkShiftDTOS
) {
}
