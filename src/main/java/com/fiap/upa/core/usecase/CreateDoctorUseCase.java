package com.fiap.upa.core.usecase;

import com.fiap.upa.core.dto.CreateDoctorDTO;
import com.fiap.upa.core.entity.Doctor;
import com.fiap.upa.core.gateway.DoctorsGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.fiap.upa.core.entity.Doctor.createNewDoctor;
import static com.fiap.upa.core.entity.DoctorSchedule.createDoctorEmptySchedule;

@Slf4j
@Service
public class CreateDoctorUseCase {

    private final DoctorsGateway doctorsGateway;

    public CreateDoctorUseCase(DoctorsGateway doctorsGateway) {
        this.doctorsGateway = doctorsGateway;
    }

    public Doctor execute(CreateDoctorDTO input) {
        log.info("Creating doctor {}", input);

        var doctor = createNewDoctor(input.name(), input.document(), input.specialty(), createDoctorEmptySchedule());

        var savedDoctor = doctorsGateway.save(doctor);

        log.info("Doctor for id {} created successfully", savedDoctor.getId());
        return doctor;
    }
}
