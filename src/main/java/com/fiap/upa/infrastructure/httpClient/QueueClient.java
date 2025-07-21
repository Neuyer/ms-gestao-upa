package com.fiap.upa.infrastructure.httpClient;

import com.fiap.upa.core.dto.AddPatientToQueueDTO;
import com.fiap.upa.core.dto.ServiceNumber;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient("queue-client")
public interface QueueClient {

    @PostMapping("queues/upas/{upaId}/add-patient")
    void add(@PathVariable("upaId") UUID upaId, @RequestBody AddPatientToQueueDTO request);

    @PostMapping("queues/upas/{upaId}/new-number")
    ResponseEntity<ServiceNumber> getNumber(@PathVariable("upaId") UUID upaId);

    @PostMapping("upas/{upaId}/generate-queues")
    void generateQueues(@PathVariable("upaId") UUID upaId);

    @PostMapping("queues/triage/upas/{upaId}/call-next")
    ResponseEntity<ServiceNumber> triage(@PathVariable("upaId") UUID upaId);

    @PostMapping("queues/service/upas/{upaId}/call-next")
    ResponseEntity<ServiceNumber> service(@PathVariable("upaId") UUID upaId);
}
