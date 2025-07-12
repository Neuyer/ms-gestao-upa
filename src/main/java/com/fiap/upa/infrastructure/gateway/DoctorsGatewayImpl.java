package com.fiap.upa.infrastructure.gateway;

import com.fiap.upa.core.entity.Doctor;
import com.fiap.upa.core.gateway.DoctorsGateway;
import com.fiap.upa.infrastructure.mapper.DoctorMapper;
import com.fiap.upa.infrastructure.repository.DoctorRepository;
import com.fiap.upa.infrastructure.repository.model.DoctorModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class DoctorsGatewayImpl implements DoctorsGateway {

    private final DoctorRepository doctorRepository;

    public DoctorsGatewayImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public DoctorModel save(Doctor doctor) {
        return doctorRepository.save(DoctorMapper.toModel(doctor));
    }

    @Override
    public List<Doctor> list() {
        return doctorRepository.findAll().stream().map(DoctorMapper::toEntity).toList();
    }

    @Override
    public List<Doctor> listAllById(List<UUID> ids) {
        return doctorRepository.findAllById(ids).stream().map(DoctorMapper::toEntity).toList();
    }

    @Override
    public Optional<Doctor> findById(UUID drId) {
        return doctorRepository.findById(drId).map(DoctorMapper::toEntity);
    }
}
