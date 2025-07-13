package com.fiap.upa.core.usecase;

import com.fiap.upa.core.entity.UPA;
import com.fiap.upa.core.gateway.UPAGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class FindUPAUseCase {

    private final UPAGateway upaGateway;

    public FindUPAUseCase(UPAGateway upaGateway) {
        this.upaGateway = upaGateway;
    }

    public UPA execute(UUID input) {
        log.info("Searching UPA for id {}", input);

        var upa = upaGateway.findById(input).orElseThrow(() -> {
            log.error("UPA not found for id: {}", input);
            return new IllegalStateException("UPA not found for id: " + input);
        });

        log.info("UPA for id {} found successfully", upa.getId());
        return upa;
    }
}
