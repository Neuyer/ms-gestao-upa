package com.fiap.upa.infrastructure.mapper;

import com.fiap.upa.core.entity.UPA;
import com.fiap.upa.infrastructure.repository.model.AttendantModel;
import com.fiap.upa.infrastructure.repository.model.DoctorModel;
import com.fiap.upa.infrastructure.repository.model.UPAModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class UPAMapper {
    private UPAMapper() {
    }

    public static UPA toEntity(UPAModel upa) {
        var doctors = Objects.isNull(upa.getDoctors()) ? new ArrayList<DoctorModel>() : upa.getDoctors();
        var attendants = Objects.isNull(upa.getAttendants()) ? new ArrayList<AttendantModel>() : upa.getAttendants();
        return UPA.loadUPA(
                upa.getId(),
                upa.getNickName(),
                AddressMapper.toEntity(upa.getAddress()),
                doctors.stream().map(DoctorMapper::toEntity).toList(),
                attendants.stream().map(AttendantMapper::toEntity).toList()
        );
    }

    public static UPAModel toModel(UPA upa) {
        return UPAModel.builder()
                .id(upa.getId())
                .nickName(upa.getNickName())
                .address(AddressMapper.toModel(upa.getAddress()))
                .doctors(upa.getDoctors().stream().map(DoctorMapper::toModel).toList())
                .attendants(upa.getAttendants().stream().map(AttendantMapper::toModel).toList())
                .build();

    }
}
