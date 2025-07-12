package com.fiap.upa.core.usecase;

import com.fiap.upa.core.dto.CreateAttendantDTO;
import com.fiap.upa.core.entity.Attendant;
import com.fiap.upa.core.gateway.AttendantGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CreateAttendantUseCase {

    private final AttendantGateway attendantGateway;

    public CreateAttendantUseCase(AttendantGateway attendantGateway) {
        this.attendantGateway = attendantGateway;
    }

    public Attendant execute(CreateAttendantDTO input) {
        log.info("Creating attendant {}", input);

        var attendant = Attendant.createNewAttendant(input.name(), input.document());

        var savedAttendant = attendantGateway.save(attendant);

        log.info("Attendant for id {} created successfully", savedAttendant.getId());
        return attendant;
    }
}
