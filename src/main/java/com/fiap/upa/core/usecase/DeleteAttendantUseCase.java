package com.fiap.upa.core.usecase;

import com.fiap.upa.core.gateway.AttendantGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class DeleteAttendantUseCase {

    private final AttendantGateway attendantGateway;

    public DeleteAttendantUseCase(AttendantGateway attendantGateway) {
        this.attendantGateway = attendantGateway;
    }

    public void execute(UUID attendantId) {
        log.info("Deleting attendant: {}", attendantId);

        attendantGateway.deleteById(attendantId);

        log.info("Attendant deleted: {}", attendantId);
    }
}
