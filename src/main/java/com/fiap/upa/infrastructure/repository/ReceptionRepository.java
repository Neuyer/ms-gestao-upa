package com.fiap.upa.infrastructure.repository;

import com.fiap.upa.core.entity.ServiceStatus;
import com.fiap.upa.infrastructure.repository.model.ReceptionModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReceptionRepository extends MongoRepository<ReceptionModel, UUID> {

    long countByUpaIdAndCreationDateBetween(UUID upaId, LocalDateTime startDate, LocalDateTime endDate);
    List<ReceptionModel> findByUpaIdAndStatusAndCreationDateBetween(UUID upaId, ServiceStatus status, LocalDateTime startDate, LocalDateTime endDate);

    List<ReceptionModel> findAllByUpaId(UUID id);
    Optional<ReceptionModel> findByServiceNumber(String serviceNumber);
}

