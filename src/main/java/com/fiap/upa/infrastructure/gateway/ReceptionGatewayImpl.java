package com.fiap.upa.infrastructure.gateway;

import com.fiap.upa.core.dto.AddPatientToQueueDTO;
import com.fiap.upa.core.entity.Reception;
import com.fiap.upa.core.gateway.ReceptionGateway;
import com.fiap.upa.infrastructure.httpClient.QueueClient;
import com.fiap.upa.infrastructure.mapper.ReceptionMapper;
import com.fiap.upa.infrastructure.repository.ReceptionRepository;
import com.fiap.upa.infrastructure.repository.model.ReceptionModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ReceptionGatewayImpl implements ReceptionGateway {

    private final ReceptionRepository receptionRepository;
    private final QueueClient queueClient;

    public ReceptionGatewayImpl(ReceptionRepository receptionRepository, QueueClient queueClient) {
        this.receptionRepository = receptionRepository;
        this.queueClient = queueClient;
    }


    @Override
    public ReceptionModel save(Reception upa) {
        return receptionRepository.save(ReceptionMapper.toModel(upa));
    }

    @Override
    public List<Reception> list() {
        return receptionRepository.findAll().stream().map(ReceptionMapper::toEntity).toList();
    }

    @Override
    public List<Reception> listAllByUpaId(UUID upaId) {
        return receptionRepository.findAllByUpaId(upaId).stream().map(ReceptionMapper::toEntity).toList();
    }

    @Override
    public List<Reception> listAllByUpaIdAndCreationTimeBetween(UUID upaId, LocalDateTime start, LocalDateTime end) {
        return receptionRepository.findByUpaIdAndCreationDateBetween(upaId, start, end).stream().map(ReceptionMapper::toEntity).toList();
    }

    @Override
    public Optional<Reception> findById(UUID upaId) {
        return  receptionRepository.findById(upaId).map(ReceptionMapper::toEntity);
    }

    @Override
    public Optional<Reception> findByServiceNumber(String receptionId) {
        return receptionRepository.findByServiceNumber(receptionId).map(ReceptionMapper::toEntity);
    }

    @Override
    public void deleteById(UUID upaId) {
        receptionRepository.deleteById(upaId);
    }

    @Override
    public void addPatientTOQueue(UUID upaId, AddPatientToQueueDTO request) {
        queueClient.add(upaId, request);
    }

    @Override
    public Long countReceptions(UUID upaId, LocalDateTime dateTime) {
        return receptionRepository.countByUpaIdAndCreationDateBetween(upaId, dateTime.withHour(0), dateTime.withHour(23));
    }
}
