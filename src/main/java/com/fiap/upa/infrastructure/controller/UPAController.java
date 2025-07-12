package com.fiap.upa.infrastructure.controller;

import com.fiap.upa.core.dto.AddAttendantsDTO;
import com.fiap.upa.core.dto.AddDoctorsDTO;
import com.fiap.upa.core.dto.CreateUPADTO;
import com.fiap.upa.core.entity.UPA;
import com.fiap.upa.core.usecase.AddAttendantUpaUseCase;
import com.fiap.upa.core.usecase.AddDoctorsUpaUseCase;
import com.fiap.upa.core.usecase.CreateUPAUseCase;
import com.fiap.upa.core.usecase.ListUPAsUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/upas")
public class UPAController {

    private final CreateUPAUseCase createUPAUseCase;
    private final ListUPAsUseCase listUPAsUseCase;
    private final AddDoctorsUpaUseCase addDoctorsUpaUseCase;
    private final AddAttendantUpaUseCase addAttendantUpaUseCase;

    public UPAController(CreateUPAUseCase createUPAUseCase, ListUPAsUseCase listUPAsUseCase, AddDoctorsUpaUseCase addDoctorsUpaUseCase, AddAttendantUpaUseCase addAttendantUpaUseCase) {
        this.createUPAUseCase = createUPAUseCase;
        this.listUPAsUseCase = listUPAsUseCase;
        this.addDoctorsUpaUseCase = addDoctorsUpaUseCase;
        this.addAttendantUpaUseCase = addAttendantUpaUseCase;
    }

    @GetMapping
    public ResponseEntity<List<UPA>> listUPAs() {
        return ResponseEntity.ok(listUPAsUseCase.execute());
    }

    @PostMapping
    public ResponseEntity<UPA> createUPA(@RequestBody CreateUPADTO createUPADTO) {
        return ResponseEntity.ok(createUPAUseCase.execute(createUPADTO));
    }

    @PostMapping("/medicos/adicionar")
    public ResponseEntity<UPA> addDoctorsUPA(@RequestBody AddDoctorsDTO addDoctorsDTO) {
        return ResponseEntity.ok(addDoctorsUpaUseCase.execute(addDoctorsDTO));
    }

    @PostMapping("/atendentes/adicionar")
    public ResponseEntity<UPA> addAttendantsUPA(@RequestBody AddAttendantsDTO addAttendantsDTO) {
        return ResponseEntity.ok(addAttendantUpaUseCase.execute(addAttendantsDTO));
    }

}
