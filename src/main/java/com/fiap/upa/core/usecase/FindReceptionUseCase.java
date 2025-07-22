package com.fiap.upa.core.usecase;

import com.fiap.upa.core.entity.Reception;
import com.fiap.upa.core.gateway.ReceptionGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class FindReceptionUseCase {

    private final ReceptionGateway receptionGateway;
    private final FindUPAUseCase findUPAUseCase;


    public FindReceptionUseCase(ReceptionGateway receptionGateway, FindUPAUseCase findUPAUseCase) {
        this.receptionGateway = receptionGateway;
        this.findUPAUseCase = findUPAUseCase;
    }


    public Reception execute(UUID upaId, String serviceNumber) {
        log.info("Finding reception: UPA - {} reception - {}", upaId, serviceNumber);

        var reception = receptionGateway.findByServiceNumber(upaId, serviceNumber).orElseThrow(() -> {
            log.error("Reception not found for number: {}", serviceNumber);
            return new IllegalStateException("Reception not found for number: " + serviceNumber);
        });
        findUPAUseCase.execute(upaId);

        log.info("Reception for number {} found successfully", reception.getServiceNumber());
        return reception;
    }
}
