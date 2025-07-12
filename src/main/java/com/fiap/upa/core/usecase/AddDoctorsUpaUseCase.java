package com.fiap.upa.core.usecase;

import com.fiap.upa.core.dto.AddDoctorsDTO;
import com.fiap.upa.core.entity.UPA;
import com.fiap.upa.core.gateway.DoctorsGateway;
import com.fiap.upa.core.gateway.UPAGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AddDoctorsUpaUseCase {

    private final UPAGateway upaGateway;
    private final DoctorsGateway doctorsGateway;

    public AddDoctorsUpaUseCase(UPAGateway upaGateway, DoctorsGateway doctorsGateway) {
        this.upaGateway = upaGateway;
        this.doctorsGateway = doctorsGateway;
    }


    public UPA execute(AddDoctorsDTO addDoctorsDTO) {
        log.info("Adding doctors:  UPA - {}, doctors {}", addDoctorsDTO.upaId(), addDoctorsDTO.doctorsIds());

        var upaId = addDoctorsDTO.upaId();
        var doctorsIds = addDoctorsDTO.doctorsIds();

        var upa = upaGateway.findById(upaId).orElseThrow(() -> {
            log.error("UPA not found for id: {}", upaId);
            return new IllegalStateException("UPA not found for id: " + upaId);
        });

        var doctors = doctorsGateway.listAllById(doctorsIds);

        doctors.forEach(upa::addDoctor);

        upaGateway.save(upa);
        log.info("Doctors added:  UPA - {}, doctors {}", upaId, doctors.size());
        return upa;
    }
}
