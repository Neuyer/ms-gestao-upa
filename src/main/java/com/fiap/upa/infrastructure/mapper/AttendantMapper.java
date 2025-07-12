package com.fiap.upa.infrastructure.mapper;

import com.fiap.upa.core.entity.Attendant;
import com.fiap.upa.infrastructure.repository.model.AttendantModel;

public abstract class AttendantMapper {
    private AttendantMapper() {
    }

    public static Attendant toEntity(AttendantModel attendant) {
        return Attendant.loadAttendant(
                attendant.getId(),
                attendant.getName(),
                attendant.getDocument()
        );
    }

    public static AttendantModel toModel(Attendant attendant) {
        return AttendantModel.builder()
                .id(attendant.getId())
                .name(attendant.getName())
                .document(attendant.getDocument().getValue())
                .build();

    }
}
