package com.fiap.upa.core.usecase;

import com.fiap.upa.core.entity.Attendant;
import com.fiap.upa.core.entity.Doctor;
import com.fiap.upa.core.gateway.AttendantGateway;
import com.fiap.upa.core.gateway.DoctorsGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ListAttendantsUseCase {

    private final AttendantGateway attendantGateway;

    public ListAttendantsUseCase(AttendantGateway attendantGateway) {
        this.attendantGateway = attendantGateway;
    }

    public List<Attendant> execute() {
        log.info("Listing all existing attendants");
        var doctors  = attendantGateway.list();

        log.info("{} attendants successfully found", doctors.size());
        return doctors;
    }
}
