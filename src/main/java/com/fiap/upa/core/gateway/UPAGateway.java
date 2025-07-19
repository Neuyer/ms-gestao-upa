package com.fiap.upa.core.gateway;

import com.fiap.upa.core.entity.SaoPauloCity;
import com.fiap.upa.core.entity.UPA;
import com.fiap.upa.infrastructure.repository.model.UPAModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UPAGateway {
    UPAModel save(UPA upa);

    List<UPA> list();

    List<UPA> listByCity(SaoPauloCity city);

    Optional<UPA> findById(UUID upaId);

    void deleteById(UUID upaId);

}
