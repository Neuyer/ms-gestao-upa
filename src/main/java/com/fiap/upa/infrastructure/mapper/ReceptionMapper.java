package com.fiap.upa.infrastructure.mapper;

import com.fiap.upa.core.entity.Reception;
import com.fiap.upa.infrastructure.repository.model.ReceptionModel;

public abstract class ReceptionMapper {
    private ReceptionMapper() {
    }

    public static Reception toEntity(ReceptionModel receptionModel) {
        return Reception.loadReception(
                receptionModel.getId(),
                receptionModel.getPatientName(),
                receptionModel.getPatientDocument(),
                receptionModel.getServiceNumber(),
                receptionModel.getSpecialty(),
                UPAMapper.toEntity(receptionModel.getUpa()),
                DoctorMapper.toEntity(receptionModel.getDoctor()),
                AttendantMapper.toEntity(receptionModel.getAttendant()),
                receptionModel.getUrgencyLevel(),
                receptionModel.getStatus(),
                receptionModel.getCreationDate(),
                receptionModel.getEndDate()
        );
    }

    public static ReceptionModel toModel(Reception reception) {
        return ReceptionModel.builder()
                .id(reception.getId())
                .patientName(reception.getPatientName())
                .patientDocument(reception.getPatientDocument())
                .upa(UPAMapper.toModel(reception.getUpa()))
                .doctor(DoctorMapper.toModel(reception.getDoctor()))
                .attendant(AttendantMapper.toModel(reception.getAttendant()))
                .serviceNumber(reception.getServiceNumber())
                .urgencyLevel(reception.getUrgencyLevel())
                .status(reception.getStatus())
                .creationDate(reception.getCreationDate())
                .endDate(reception.getEndDate())
                .specialty(reception.getSpecialty())
                .build();

    }
}
