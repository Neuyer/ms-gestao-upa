package com.fiap.upa.infrastructure.controller;

import com.fiap.upa.core.dto.AddAttendantsDTO;
import com.fiap.upa.core.dto.AddDoctorsDTO;
import com.fiap.upa.core.dto.CreateReceptionDTO;
import com.fiap.upa.core.dto.CreateUPADTO;
import com.fiap.upa.core.entity.Reception;
import com.fiap.upa.core.entity.SaoPauloCity;
import com.fiap.upa.core.entity.UPA;
import com.fiap.upa.core.usecase.*;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/upas")
public class UPAController {

    private final CreateUPAUseCase createUPAUseCase;
    private final ListUPAsUseCase listUPAsUseCase;
    private final ListUPAsByCityUseCase listUPAsByCityUseCase;
    private final FindUPAUseCase findUPAUseCase;
    private final ListUPAReceptionsUseCase listUPAReceptionsUseCase;
    private final AddDoctorsUpaUseCase addDoctorsUpaUseCase;
    private final AddAttendantUpaUseCase addAttendantUpaUseCase;
    private final DeleteUPAUseCase deleteUPAUseCase;
    private final CreateReceptionUseCase createReceptionUseCase;
    private final StartReceptionUseCase startReceptionUseCase;
    private final FindReceptionUseCase findReceptionUseCase;
    private final FindReceptionByTimeRangeUseCase findReceptionByTimeRangeUseCase;
    private final EndReceptionUseCase endReceptionUseCase;

    public UPAController(CreateUPAUseCase createUPAUseCase, ListUPAsUseCase listUPAsUseCase, ListUPAsByCityUseCase listUPAsByCityUseCase, FindUPAUseCase findUPAUseCase, ListUPAReceptionsUseCase listUPAReceptionsUseCase, AddDoctorsUpaUseCase addDoctorsUpaUseCase, AddAttendantUpaUseCase addAttendantUpaUseCase, DeleteUPAUseCase deleteUPAUseCase, CreateReceptionUseCase createReceptionUseCase, StartReceptionUseCase startReceptionUseCase, FindReceptionUseCase findReceptionUseCase, FindReceptionByTimeRangeUseCase findReceptionByTimeRangeUseCase, EndReceptionUseCase endReceptionUseCase) {
        this.createUPAUseCase = createUPAUseCase;
        this.listUPAsUseCase = listUPAsUseCase;
        this.listUPAsByCityUseCase = listUPAsByCityUseCase;
        this.findUPAUseCase = findUPAUseCase;
        this.listUPAReceptionsUseCase = listUPAReceptionsUseCase;
        this.addDoctorsUpaUseCase = addDoctorsUpaUseCase;
        this.addAttendantUpaUseCase = addAttendantUpaUseCase;
        this.deleteUPAUseCase = deleteUPAUseCase;
        this.createReceptionUseCase = createReceptionUseCase;
        this.startReceptionUseCase = startReceptionUseCase;
        this.findReceptionUseCase = findReceptionUseCase;
        this.findReceptionByTimeRangeUseCase = findReceptionByTimeRangeUseCase;
        this.endReceptionUseCase = endReceptionUseCase;
    }

    @GetMapping
    public ResponseEntity<List<UPA>> listUPAs() {
        return ResponseEntity.ok(listUPAsUseCase.execute());
    }

    @PostMapping
    public ResponseEntity<UPA> createUPA(@RequestBody CreateUPADTO createUPADTO) {
        return ResponseEntity.ok(createUPAUseCase.execute(createUPADTO));
    }

    @GetMapping("/cidades/{cidade}")
    public ResponseEntity<List<UPA>> findUpa(@PathVariable SaoPauloCity cidade) {
        return ResponseEntity.ok(listUPAsByCityUseCase.execute(cidade));
    }

    @GetMapping("{upaId}")
    public ResponseEntity<UPA> findUpa(@PathVariable UUID upaId) {
        return ResponseEntity.ok(findUPAUseCase.execute(upaId));
    }

    @GetMapping("{upaId}/atendimentos")
    public ResponseEntity<List<Reception>> findUpaReceptions(@PathVariable UUID upaId) {
        return ResponseEntity.ok(listUPAReceptionsUseCase.execute(upaId));
    }

    @PostMapping("{upaId}/atendimentos")
    public ResponseEntity<Reception> createUpaReception(@PathVariable UUID upaId, @RequestBody CreateReceptionDTO createReceptionDTO) {
        return ResponseEntity.ok(createReceptionUseCase.execute(upaId, createReceptionDTO));
    }

    @GetMapping("{upaId}/atendimentos/{numeroServico}")
    public ResponseEntity<Reception> createUpaReception(@PathVariable UUID upaId, @PathVariable String numeroServico) {
        return ResponseEntity.ok(findReceptionUseCase.execute(upaId, numeroServico));
    }

    @GetMapping("{upaId}/atendimentos/periodo")
    public ResponseEntity<List<Reception>> createUpaReception(@PathVariable UUID upaId, @RequestParam("inicio") LocalDateTime inicio, @RequestParam("fim") LocalDateTime fim) {
        return ResponseEntity.ok(findReceptionByTimeRangeUseCase.execute(upaId, inicio, fim));
    }

    @PatchMapping("{upaId}/atendimentos/{numeroServico}/iniciar-atendimento")
    public ResponseEntity<Reception> startUpaReception(@PathVariable UUID upaId, @PathVariable String numeroServico) {
        return ResponseEntity.ok(startReceptionUseCase.execute(upaId, numeroServico));
    }

    @PatchMapping("{upaId}/atendimentos/{numeroServico}/finalizar-atendimento")
    public ResponseEntity<Reception> endUpaReception(@PathVariable UUID upaId, @PathVariable String numeroServico) {
        return ResponseEntity.ok(endReceptionUseCase.execute(upaId, numeroServico));
    }

    @PostMapping("/medicos/adicionar")
    public ResponseEntity<UPA> addDoctorsUPA(@RequestBody AddDoctorsDTO addDoctorsDTO) {
        return ResponseEntity.ok(addDoctorsUpaUseCase.execute(addDoctorsDTO));
    }

    @PostMapping("/atendentes/adicionar")
    public ResponseEntity<UPA> addAttendantsUPA(@RequestBody AddAttendantsDTO addAttendantsDTO) {
        return ResponseEntity.ok(addAttendantUpaUseCase.execute(addAttendantsDTO));
    }

    @DeleteMapping("{upaId}")
    public ResponseEntity<Void> delete(@PathVariable UUID upaId) {
        deleteUPAUseCase.execute(upaId);
        return ResponseEntity.ok().build();
    }

}
