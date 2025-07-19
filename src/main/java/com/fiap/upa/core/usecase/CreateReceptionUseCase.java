package com.fiap.upa.core.usecase;

import com.fiap.upa.core.dto.AddPatientToQueueDTO;
import com.fiap.upa.core.dto.CreateReceptionDTO;
import com.fiap.upa.core.entity.Reception;
import com.fiap.upa.core.gateway.ReceptionGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.fiap.upa.core.entity.ServiceNumberGenerator.generateServiceNumber;

@Slf4j
@Service
public class CreateReceptionUseCase {

    private final ReceptionGateway receptionGateway;
    private final FindUPAUseCase findUPAUseCase;
    private final FindDoctorUseCase findDoctorUseCase;
    private final FindAttendantUseCase findAttendantUseCase;


    public CreateReceptionUseCase(ReceptionGateway receptionGateway, FindUPAUseCase findUPAUseCase, FindDoctorUseCase findDoctorUseCase, FindAttendantUseCase findAttendantUseCase) {
        this.receptionGateway = receptionGateway;
        this.findUPAUseCase = findUPAUseCase;
        this.findDoctorUseCase = findDoctorUseCase;

        this.findAttendantUseCase = findAttendantUseCase;
    }


    public Reception execute(UUID upaId, CreateReceptionDTO input) {
        log.info("Creating reception {}", input);

        var receptionsCount = receptionGateway.countReceptions(upaId, LocalDateTime.now());

        var serviceNumber = generateServiceNumber(receptionsCount);

        var reception = Reception.createNewReception(
                input.patientName(),
                input.patientDocument(),
                serviceNumber,
                input.specialty(),
                input.urgencyLevel());

        var upa = findUPAUseCase.execute(upaId);
        var doctor = findDoctorUseCase.execute(input.doctorId());
        var attendant = findAttendantUseCase.execute(input.attendantId());

        reception.updateUpa(upa);
        reception.updateDoctor(doctor);
        reception.updateAttendant(attendant);

        var savedReception = receptionGateway.save(reception);

        try {
            AddPatientToQueueDTO.PatientRequest patientRequest = new AddPatientToQueueDTO.PatientRequest(input.patientName(), savedReception.getServiceNumber());
            AddPatientToQueueDTO req = new AddPatientToQueueDTO(patientRequest, input.urgencyLevel());
            receptionGateway.addPatientTOQueue(upaId, req);
        } catch (Exception e) {
            log.info("Reception for id {} not send to queue - ex: {}", savedReception.getId(), e);
        }

        log.info("Reception for id {} created successfully", savedReception.getId());
        return reception;
    }
}
