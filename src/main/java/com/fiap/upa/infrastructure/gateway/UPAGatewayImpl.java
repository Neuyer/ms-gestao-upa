package com.fiap.upa.infrastructure.gateway;

import com.fiap.upa.core.entity.SaoPauloCity;
import com.fiap.upa.core.entity.UPA;
import com.fiap.upa.core.gateway.UPAGateway;
import com.fiap.upa.infrastructure.mapper.UPAMapper;
import com.fiap.upa.infrastructure.repository.UPARepository;
import com.fiap.upa.infrastructure.repository.model.UPAModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UPAGatewayImpl implements UPAGateway {

    private final UPARepository upaRepository;

    public UPAGatewayImpl(UPARepository upaRepository) {
        this.upaRepository = upaRepository;
    }

    @Override
    public UPAModel save(UPA upa) {
        return upaRepository.save(UPAMapper.toModel(upa));
    }

    @Override
    public List<UPA> list() {
        return upaRepository.findAll().stream().map(UPAMapper::toEntity).toList();
    }

    @Override
    public List<UPA> listByCity(SaoPauloCity city) {
        return upaRepository.findByAddress_City(city.getCityName()).stream().map(UPAMapper::toEntity).toList();
    }

    @Override
    public Optional<UPA> findById(UUID upaId) {
        return  upaRepository.findById(upaId).map(UPAMapper::toEntity);
    }

    @Override
    public void deleteById(UUID upaId) {
        upaRepository.deleteById(upaId);
    }
}
