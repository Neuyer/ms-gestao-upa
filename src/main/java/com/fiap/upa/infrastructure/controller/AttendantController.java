package com.fiap.upa.infrastructure.controller;

import com.fiap.upa.core.dto.CreateAttendantDTO;
import com.fiap.upa.core.entity.Attendant;
import com.fiap.upa.core.usecase.CreateAttendantUseCase;
import com.fiap.upa.core.usecase.FindAttendantUseCase;
import com.fiap.upa.core.usecase.ListAttendantsUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/atendentes")
public class AttendantController {

    private final CreateAttendantUseCase createAttendantUseCase;
    private final ListAttendantsUseCase listAttendantsUseCase;
    private final FindAttendantUseCase findAttendantUseCase;

    public AttendantController(CreateAttendantUseCase createAttendantUseCase, ListAttendantsUseCase listAttendantsUseCase, FindAttendantUseCase findAttendantUseCase) {
        this.createAttendantUseCase = createAttendantUseCase;
        this.listAttendantsUseCase = listAttendantsUseCase;
        this.findAttendantUseCase = findAttendantUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Attendant>> listAttendants() {
        return ResponseEntity.ok(listAttendantsUseCase.execute());
    }

    @GetMapping("{attendantId}")
    public ResponseEntity<Attendant> findById(@PathVariable UUID attendantId) {
        return ResponseEntity.ok(findAttendantUseCase.execute(attendantId));
    }

    @PostMapping
    public ResponseEntity<Attendant> createAttendant(@RequestBody CreateAttendantDTO createAttendantDTO) {
        return ResponseEntity.ok(createAttendantUseCase.execute(createAttendantDTO));
    }

}
