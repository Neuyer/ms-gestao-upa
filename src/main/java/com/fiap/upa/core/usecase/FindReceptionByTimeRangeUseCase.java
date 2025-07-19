package com.fiap.upa.core.usecase;

import com.fiap.upa.core.entity.Reception;
import com.fiap.upa.core.gateway.ReceptionGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class FindReceptionByTimeRangeUseCase {

    private final ReceptionGateway receptionGateway;
    private final FindUPAUseCase findUPAUseCase;


    public FindReceptionByTimeRangeUseCase(ReceptionGateway receptionGateway, FindUPAUseCase findUPAUseCase) {
        this.receptionGateway = receptionGateway;
        this.findUPAUseCase = findUPAUseCase;
    }


    public List<Reception> execute(UUID upaId, LocalDateTime start, LocalDateTime end) {
        log.info("Finding receptions: UPA - {} between - {} and {}", upaId, start, end);

        var receptions = receptionGateway.listAllByUpaIdAndCreationTimeBetween(upaId, start, end);

        findUPAUseCase.execute(upaId);

        log.info("Receptions for upa {} found successfully", upaId);
        return receptions;
    }
}
