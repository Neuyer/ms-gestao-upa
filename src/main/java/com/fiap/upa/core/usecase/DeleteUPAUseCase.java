package com.fiap.upa.core.usecase;

import com.fiap.upa.core.gateway.UPAGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class DeleteUPAUseCase {

    private final UPAGateway upaGateway;

    public DeleteUPAUseCase(UPAGateway upaGateway) {
        this.upaGateway = upaGateway;
    }

    public void execute(UUID upaId) {
        log.info("Deleting UPA: {}", upaId);

        upaGateway.deleteById(upaId);

        log.info("UPA deleted: {}", upaId);
    }
}
