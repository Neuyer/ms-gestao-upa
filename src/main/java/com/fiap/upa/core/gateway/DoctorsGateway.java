package com.fiap.upa.core.gateway;

import com.fiap.upa.core.entity.Doctor;
import com.fiap.upa.infrastructure.repository.model.DoctorModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DoctorsGateway {
    DoctorModel save(Doctor doctor);

    List<Doctor> list();

    List<Doctor> listAllById(List<UUID> uuids);

    Optional<Doctor> findById(UUID drId);

}
