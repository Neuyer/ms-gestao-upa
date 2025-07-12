package com.fiap.upa.infrastructure.repository;

import com.fiap.upa.infrastructure.repository.model.DoctorModel;
import com.fiap.upa.infrastructure.repository.model.UPAModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DoctorRepository extends MongoRepository<DoctorModel, UUID> {
}

