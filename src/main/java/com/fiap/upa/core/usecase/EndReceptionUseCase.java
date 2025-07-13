package com.fiap.upa.core.usecase;

import com.fiap.upa.core.entity.Reception;
import com.fiap.upa.core.gateway.ReceptionGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class EndReceptionUseCase {

    private final ReceptionGateway receptionGateway;
    private final FindReceptionUseCase findReceptionUseCase;


    public EndReceptionUseCase(ReceptionGateway receptionGateway, FindReceptionUseCase findReceptionUseCase) {
        this.receptionGateway = receptionGateway;
        this.findReceptionUseCase = findReceptionUseCase;
    }


    public Reception execute(UUID upaId, String receptionId) {
        log.info("Ending reception {}", receptionId);

        var reception = findReceptionUseCase.execute(upaId, receptionId);

        reception.endService();

        receptionGateway.save(reception);

        log.info("Reception for id {} ended successfully", reception.getId());
        return reception;
    }
}
