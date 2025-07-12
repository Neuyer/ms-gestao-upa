package com.fiap.upa.core.usecase;

import com.fiap.upa.core.dto.CreateUPADTO;
import com.fiap.upa.core.entity.UPA;
import com.fiap.upa.core.gateway.UPAGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.fiap.upa.core.entity.Address.createNewAddress;
import static com.fiap.upa.core.entity.UPA.createNewUPA;

@Slf4j
@Service
public class CreateUPAUseCase {

    private final UPAGateway UPAGateway;

    public CreateUPAUseCase(UPAGateway UPAGateway) {
        this.UPAGateway = UPAGateway;
    }

    public UPA execute(CreateUPADTO input) {
        log.info("Creating UPA {}", input);

        var inputAddress = input.address();

        var address = createNewAddress(inputAddress.street(), inputAddress.city(), inputAddress.state(), inputAddress.zipCode(), inputAddress.number());

        var upa = createNewUPA(input.nickName(), address);

        var savedUpa  = UPAGateway.save(upa);

        log.info("UPA for id {} created successfully", savedUpa.getId());
        return upa;
    }
}
