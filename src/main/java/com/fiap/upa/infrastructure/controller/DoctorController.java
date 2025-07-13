package com.fiap.upa.infrastructure.controller;

import com.fiap.upa.core.dto.CreateDoctorDTO;
import com.fiap.upa.core.dto.CreateDoctorScheduleDTO;
import com.fiap.upa.core.entity.Doctor;
import com.fiap.upa.core.usecase.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/medicos")
public class DoctorController {

    private final CreateDoctorUseCase createDoctorUseCase;
    private final ListDoctorsUseCase listDoctorsUseCase;
    private final FindDoctorUseCase findDoctorUseCase;
    private final CreateDoctorScheduleUseCase createDoctorScheduleUseCase;
    private final DeleteDoctorUseCase deleteDoctorUseCase;

    public DoctorController(CreateDoctorUseCase createDoctorUseCase, ListDoctorsUseCase listDoctorsUseCase, FindDoctorUseCase findDoctorUseCase, CreateDoctorScheduleUseCase createDoctorScheduleUseCase, DeleteDoctorUseCase deleteDoctorUseCase) {
        this.createDoctorUseCase = createDoctorUseCase;
        this.listDoctorsUseCase = listDoctorsUseCase;
        this.findDoctorUseCase = findDoctorUseCase;
        this.createDoctorScheduleUseCase = createDoctorScheduleUseCase;
        this.deleteDoctorUseCase = deleteDoctorUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> listDoctors() {
        return ResponseEntity.ok(listDoctorsUseCase.execute());
    }

    @GetMapping("{doctorId}")
    public ResponseEntity<Doctor> findById(@PathVariable UUID doctorId) {
        return ResponseEntity.ok(findDoctorUseCase.execute(doctorId));
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody CreateDoctorDTO createDoctorDTO) {
        return ResponseEntity.ok(createDoctorUseCase.execute(createDoctorDTO));
    }

    @PostMapping("/escalas")
    public ResponseEntity<Doctor> createDoctorSchedule(@RequestBody CreateDoctorScheduleDTO createDoctorDTO) {
        return ResponseEntity.ok(createDoctorScheduleUseCase.execute(createDoctorDTO));
    }

    @DeleteMapping("{doctorId}")
    public ResponseEntity<Void> delete(@PathVariable UUID doctorId) {
        deleteDoctorUseCase.execute(doctorId);
        return ResponseEntity.ok().build();
    }

}
