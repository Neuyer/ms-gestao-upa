package com.fiap.upa.infrastructure.gateway;

import com.fiap.upa.core.entity.Attendant;
import com.fiap.upa.core.gateway.AttendantGateway;
import com.fiap.upa.infrastructure.mapper.AttendantMapper;
import com.fiap.upa.infrastructure.repository.AttendantRepository;
import com.fiap.upa.infrastructure.repository.model.AttendantModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class AttendantsGatewayImpl implements AttendantGateway {

    private final AttendantRepository attendantRepository;

    public AttendantsGatewayImpl(AttendantRepository attendantRepository) {
        this.attendantRepository = attendantRepository;
    }

    @Override
    public AttendantModel save(Attendant attendant) {
        return attendantRepository.save(AttendantMapper.toModel(attendant));
    }

    @Override
    public List<Attendant> list() {
        return attendantRepository.findAll().stream().map(AttendantMapper::toEntity).toList();
    }

    @Override
    public List<Attendant> listAllById(List<UUID> uuids) {
        return attendantRepository.findAllById(uuids).stream().map(AttendantMapper::toEntity).toList();
    }

    @Override
    public Optional<Attendant> findById(UUID drId) {
        return attendantRepository.findById(drId).map(AttendantMapper::toEntity);
    }

    @Override
    public void deleteById(UUID attendantId) {
        attendantRepository.deleteById(attendantId);
    }
}
