package com.fiap.upa.infrastructure.httpClient;

import com.fiap.upa.core.dto.AddPatientToQueueDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient("queue-client")
public interface QueueClient {

    @PostMapping("/queues/upas/{upaId}/add-patient")
    void add(@PathVariable("upaId") UUID upaId, @RequestBody AddPatientToQueueDTO request);
}
