package com.fiap.upa.core.usecase;

import com.fiap.upa.core.dto.AddAttendantsDTO;
import com.fiap.upa.core.entity.UPA;
import com.fiap.upa.core.gateway.AttendantGateway;
import com.fiap.upa.core.gateway.UPAGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AddAttendantUpaUseCase {

    private final UPAGateway upaGateway;
    private final AttendantGateway attendantGateway;

    public AddAttendantUpaUseCase(UPAGateway upaGateway, AttendantGateway attendantGateway) {
        this.upaGateway = upaGateway;

        this.attendantGateway = attendantGateway;
    }


    public UPA execute(AddAttendantsDTO addAttendantsDTO) {
        log.info("Adding doctors:  UPA - {}, attendants {}", addAttendantsDTO.upaId(), addAttendantsDTO.attendantsIds());

        var upaId = addAttendantsDTO.upaId();
        var attendantsIds = addAttendantsDTO.attendantsIds();

        var upa = upaGateway.findById(upaId).orElseThrow(() -> {
            log.error("UPA not found for id: {}", upaId);
            return new IllegalStateException("UPA not found for id: " + upaId);
        });

        var attendants = attendantGateway.listAllById(attendantsIds);

        attendants.forEach(upa::addDAttendant);

        upaGateway.save(upa);
        log.info("Attendants added:  UPA - {}, attendants {}", upaId, attendants.size());
        return upa;
    }
}
