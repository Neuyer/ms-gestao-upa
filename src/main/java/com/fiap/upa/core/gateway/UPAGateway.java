package com.fiap.upa.core.gateway;

import com.fiap.upa.core.entity.UPA;
import com.fiap.upa.infrastructure.repository.model.UPAModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UPAGateway {
    UPAModel save(UPA ubs);

    List<UPA> list();

    Optional<UPA> findById(UUID upaId);

}
