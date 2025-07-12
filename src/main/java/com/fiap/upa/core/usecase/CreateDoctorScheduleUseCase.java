package com.fiap.upa.core.usecase;

import com.fiap.upa.core.dto.CreateDoctorScheduleDTO;
import com.fiap.upa.core.entity.Doctor;
import com.fiap.upa.core.gateway.DoctorsGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.fiap.upa.core.entity.DoctorSchedule.createDoctorSchedule;
import static com.fiap.upa.core.entity.WorkShift.createWorkShift;

@Slf4j
@Service
public class CreateDoctorScheduleUseCase {

    private final DoctorsGateway doctorsGateway;

    public CreateDoctorScheduleUseCase(DoctorsGateway doctorsGateway) {
        this.doctorsGateway = doctorsGateway;
    }

    public Doctor execute(CreateDoctorScheduleDTO input) {
        log.info("Creating doctor schedule {}", input);

        var workShifts = input.createWorkShiftDTOS()
                .stream()
                .map(createWorkShiftDTO ->
                        createWorkShift(createWorkShiftDTO.start(), createWorkShiftDTO.end()))
                .toList();

        var schedule = createDoctorSchedule(workShifts);

        var doctor = doctorsGateway.findById(input.doctorId()).orElseThrow(() -> {
            log.error("Doctor not found for id: {}", input.doctorId());
            return new IllegalStateException("Doctor not found for id: " + input.doctorId());
        });

        doctor.updateScheduler(schedule);
        doctorsGateway.save(doctor);

        log.info("Doctor scheduler for id {} created successfully", doctor.getId());
        return doctor;
    }
}
