package com.fiap.upa.core.usecase;

import com.fiap.upa.core.entity.UPA;
import com.fiap.upa.core.gateway.UPAGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ListUPAsUseCase {

    private final UPAGateway UPAGateway;

    public ListUPAsUseCase(UPAGateway UPAGateway) {
        this.UPAGateway = UPAGateway;
    }

    public List<UPA> execute() {
        log.info("Listing all existing UPAs");
        var upas = UPAGateway.list();
        log.info("{} found successfully", upas.size());
        return upas;
    }
}
