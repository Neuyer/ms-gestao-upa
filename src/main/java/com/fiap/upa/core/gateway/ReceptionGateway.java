package com.fiap.upa.core.gateway;

import com.fiap.upa.core.dto.AddPatientToQueueDTO;
import com.fiap.upa.core.entity.Reception;
import com.fiap.upa.infrastructure.repository.model.ReceptionModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReceptionGateway {
    ReceptionModel save(Reception reception);

    List<Reception> list();

    List<Reception> listAllByUpaId(UUID upaId);

    List<Reception> listAllByUpaIdAndCreationTimeBetween(UUID upaId, LocalDateTime start, LocalDateTime end);

    Optional<Reception> findById(UUID receptionId);

    Optional<Reception> findByServiceNumber(String receptionId);

    void deleteById(UUID upaId);

    void addPatientTOQueue(UUID upaId, AddPatientToQueueDTO request);

    Long countReceptions(UUID upaId, LocalDateTime dateTime);

}
