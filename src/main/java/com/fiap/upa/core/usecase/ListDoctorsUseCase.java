package com.fiap.upa.core.usecase;

import com.fiap.upa.core.entity.Doctor;
import com.fiap.upa.core.entity.UPA;
import com.fiap.upa.core.gateway.DoctorsGateway;
import com.fiap.upa.core.gateway.UPAGateway;
import com.fiap.upa.infrastructure.mapper.DoctorMapper;
import com.fiap.upa.infrastructure.mapper.UPAMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ListDoctorsUseCase {

    private final DoctorsGateway doctorsGateway;

    public ListDoctorsUseCase(DoctorsGateway doctorsGateway) {
        this.doctorsGateway = doctorsGateway;
    }

    public List<Doctor> execute() {
        log.info("Listing all existing doctors");
        var doctors  = doctorsGateway.list();

        log.info("{} doctors successfully found", doctors.size());
        return doctors;
    }
}
