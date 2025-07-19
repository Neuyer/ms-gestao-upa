package com.fiap.upa.core.usecase;

import com.fiap.upa.core.entity.SaoPauloCity;
import com.fiap.upa.core.entity.UPA;
import com.fiap.upa.core.gateway.UPAGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ListUPAsByCityUseCase {

    private final UPAGateway UPAGateway;

    public ListUPAsByCityUseCase(UPAGateway UPAGateway) {
        this.UPAGateway = UPAGateway;
    }

    public List<UPA> execute(SaoPauloCity city) {
        log.info("Listing all existing UPAs from {}", city);
        var upas = UPAGateway.listByCity(city);
        log.info("{} found successfully in {}", upas.size(), city);
        return upas;
    }
}
