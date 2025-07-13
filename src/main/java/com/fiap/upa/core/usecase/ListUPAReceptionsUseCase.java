package com.fiap.upa.core.usecase;

import com.fiap.upa.core.entity.Reception;
import com.fiap.upa.core.gateway.ReceptionGateway;
import com.fiap.upa.core.gateway.UPAGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ListUPAReceptionsUseCase {

    private final UPAGateway UPAGateway;
    private final ReceptionGateway receptionGateway;

    public ListUPAReceptionsUseCase(UPAGateway UPAGateway, ReceptionGateway receptionGateway) {
        this.UPAGateway = UPAGateway;
        this.receptionGateway = receptionGateway;
    }

    public List<Reception> execute(UUID input) {
        log.info("Listing all existing receptions for UPA: {}", input);

        var upa = UPAGateway.findById(input).orElseThrow(() -> {
            log.error("UPA not found for id: {}", input);
            return new IllegalStateException("UPA not found for id: " + input);
        });

        var receptions = receptionGateway.listAllByUpaId(upa.getId());
        log.info("{} receptions found successfully", receptions.size());
        return receptions;
    }
}
