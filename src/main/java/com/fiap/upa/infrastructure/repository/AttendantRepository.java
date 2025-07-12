package com.fiap.upa.infrastructure.repository;

import com.fiap.upa.infrastructure.repository.model.AttendantModel;
import com.fiap.upa.infrastructure.repository.model.DoctorModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AttendantRepository extends MongoRepository<AttendantModel, UUID> {
}

