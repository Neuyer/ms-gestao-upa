package com.fiap.upa.core.gateway;

import com.fiap.upa.core.entity.Attendant;
import com.fiap.upa.infrastructure.repository.model.AttendantModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AttendantGateway {
    AttendantModel save(Attendant doctor);

    List<Attendant> list();

    List<Attendant> listAllById(List<UUID> uuids);

    Optional<Attendant> findById(UUID drId);

}
