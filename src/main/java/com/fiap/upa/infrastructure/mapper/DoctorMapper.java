package com.fiap.upa.infrastructure.mapper;

import com.fiap.upa.core.entity.Doctor;
import com.fiap.upa.infrastructure.repository.model.DoctorModel;

public abstract class DoctorMapper {
    private DoctorMapper() {
    }

    public static Doctor toEntity(DoctorModel doctorModel) {
        return Doctor.loadDoctor(
                doctorModel.getId(),
                doctorModel.getName(),
                doctorModel.getDocument(),
                doctorModel.getSpecialty(),
                doctorModel.isAvailable(),
                doctorModel.getDoctorSchedule()
        );
    }

    public static DoctorModel toModel(Doctor doctor) {
        return DoctorModel.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .specialty(doctor.getSpecialty())
                .document(doctor.getDocument().getValue())
                .isAvailable(doctor.getAvailable())
                .doctorSchedule(doctor.getSchedule())
                .build();

    }
}
