package com.fiap.upa.infrastructure.repository;

import com.fiap.upa.infrastructure.repository.model.UPAModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UPARepository extends MongoRepository<UPAModel, UUID> {
    List<UPAModel> findByAddress_City(String addressCity);
}

