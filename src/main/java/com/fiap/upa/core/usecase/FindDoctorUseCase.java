package com.fiap.upa.core.usecase;

import com.fiap.upa.core.entity.Doctor;
import com.fiap.upa.core.gateway.DoctorsGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class FindDoctorUseCase {

    private final DoctorsGateway doctorsGateway;

    public FindDoctorUseCase(DoctorsGateway doctorsGateway) {
        this.doctorsGateway = doctorsGateway;
    }

    public Doctor execute(UUID input) {
        log.info("Searching doctor for id {}", input);

        var doctor = doctorsGateway.findById(input).orElseThrow(() -> {
            log.error("Doctor not found for id: {}", input);
            return new IllegalStateException("Doctor not found for id: " + input);
        });

        log.info("Doctor for id {} found successfully", doctor.getId());
        return doctor;
    }
}
