package com.fiap.upa.core.usecase;

import com.fiap.upa.core.gateway.DoctorsGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class DeleteDoctorUseCase {

    private final DoctorsGateway doctorsGateway;

    public DeleteDoctorUseCase(DoctorsGateway doctorsGateway) {
        this.doctorsGateway = doctorsGateway;
    }

    public void execute(UUID doctorID) {
        log.info("Deleting doctor: {}", doctorID);

        doctorsGateway.deleteById(doctorID);

        log.info("Doctor deleted: {}", doctorID);
    }
}
