package com.fiap.upa.core.usecase;

import com.fiap.upa.core.entity.Attendant;
import com.fiap.upa.core.gateway.AttendantGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class FindAttendantUseCase {

    private final AttendantGateway attendantGateway;

    public FindAttendantUseCase(AttendantGateway attendantGateway) {
        this.attendantGateway = attendantGateway;
    }

    public Attendant execute(UUID input) {
        log.info("Searching attendant for id {}", input);

        var attendant = attendantGateway.findById(input).orElseThrow(() -> {
            log.error("attendant not found for id: {}", input);
            return new IllegalStateException("attendant not found for id: " + input);
        });

        log.info("Attendant for id {} found successfully", attendant.getId());
        return attendant;
    }
}
