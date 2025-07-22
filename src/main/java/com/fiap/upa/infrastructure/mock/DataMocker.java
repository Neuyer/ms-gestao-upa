package com.fiap.upa.infrastructure.mock;

import com.fiap.upa.core.dto.AddPatientToQueueDTO;
import com.fiap.upa.core.dto.CreateReceptionDTO;
import com.fiap.upa.core.entity.Reception;
import com.fiap.upa.core.entity.ServiceStatus;
import com.fiap.upa.core.entity.Urgency;
import com.fiap.upa.core.usecase.CreateReceptionUseCase;
import com.fiap.upa.core.usecase.EndReceptionUseCase;
import com.fiap.upa.core.usecase.FindReceptionUseCase;
import com.fiap.upa.core.usecase.StartReceptionUseCase;
import com.fiap.upa.infrastructure.httpClient.QueueClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import static org.bouncycastle.pqc.legacy.math.linearalgebra.IntegerFunctions.mod;

@Component
@Slf4j
public class DataMocker implements CommandLineRunner {

    private final QueueClient queueClient;
    private final CreateReceptionUseCase createReceptionUseCase;
    private final StartReceptionUseCase startReceptionUseCase;
    private final EndReceptionUseCase endReceptionUseCase;
    private final FindReceptionUseCase findReceptionUseCase;

    Set<Reception> receptions = new LinkedHashSet<>();

    public DataMocker(QueueClient queueClient, CreateReceptionUseCase createReceptionUseCase, StartReceptionUseCase startReceptionUseCase, EndReceptionUseCase endReceptionUseCase, FindReceptionUseCase findReceptionUseCase) {
        this.queueClient = queueClient;
        this.createReceptionUseCase = createReceptionUseCase;
        this.startReceptionUseCase = startReceptionUseCase;
        this.endReceptionUseCase = endReceptionUseCase;
        this.findReceptionUseCase = findReceptionUseCase;
    }

    @Async
    void doStuff() throws InterruptedException {
        var upaId = UUID.fromString("cac6a655-906b-4a8e-b856-4dc7af494393");
        try {
            queueClient.generateQueues(upaId);
        } catch (Exception e) {
        }
        for (int i = 0; i < 40; i++) {
            var doctorId = UUID.fromString("1346394e-28a7-bccf-167e-c3b520f9bc96");
            var attendantId = UUID.fromString("a74d9190-7a5f-4d7c-9178-aafc0aeb3429");
            var name = "Patient " + (int) (Math.random() * 1500);
            var cpf = cpf();

            var number = queueClient.getNumber(upaId);
            var triagemNumber = queueClient.triage(upaId);

            var reception = new CreateReceptionDTO(name, cpf, "Clinico geral", doctorId, attendantId, randomUrgency());
            var res = createReceptionUseCase.execute(upaId, reception);

            Thread.sleep((long) (Math.random() * 2500));
            this.receptions.add(res);
            addPatient(upaId, res);
        }
    }


    @Async
    void addPatient(UUID upaId, Reception reception) throws InterruptedException {
        Thread.sleep((long) (Math.random() * 2500));
        queueClient.service(upaId);
        startReceptionUseCase.execute(upaId, reception.getServiceNumber());
        geraTriagem();
    }

    @Async
    void end() {
        geraTriagem();
        var upaId = UUID.fromString("cac6a655-906b-4a8e-b856-4dc7af494393");
        receptions.forEach(reception -> {
                    try {
                        Thread.sleep((long) (Math.random() * 5000));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    var res = findReceptionUseCase.execute(upaId, reception.getServiceNumber());
                    if (ServiceStatus.ONGOING.equals(res.getStatus()))
                        endReceptionUseCase.execute(upaId, reception.getServiceNumber());
                }
        );

    }

    @Async
    void geraTriagem() {
        var upaId = UUID.fromString("cac6a655-906b-4a8e-b856-4dc7af494393");
        for (int i = 0; i < 100; i++) {
            queueClient.getNumber(upaId);
        }

    }

    private Urgency randomUrgency() {
        Random PRNG = new Random();
        Urgency[] urgencies = Urgency.values();
        return urgencies[PRNG.nextInt(urgencies.length)];

    }

    private String cpf() {
        int n = 9;
        int n1 = randomiza(n);
        int n2 = randomiza(n);
        int n3 = randomiza(n);
        int n4 = randomiza(n);
        int n5 = randomiza(n);
        int n6 = randomiza(n);
        int n7 = randomiza(n);
        int n8 = randomiza(n);
        int n9 = randomiza(n);
        int d1 = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10;

        d1 = (int) (11 - (mod(d1, 11)));

        if (d1 >= 10)
            d1 = 0;

        int d2 = d1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11;

        d2 = (int) (11 - (mod(d2, 11)));

        String retorno = null;

        if (d2 >= 10)
            d2 = 0;
        retorno = "";

        if (false)
            retorno = "" + n1 + n2 + n3 + '.' + n4 + n5 + n6 + '.' + n7 + n8 + n9 + '-' + d1 + d2;
        else
            retorno = "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + d1 + d2;

        return retorno;
    }

    private int randomiza(int n) {
        int ranNum = (int) (Math.random() * n);
        return ranNum;
    }

    @Override
    public void run(String... args) throws Exception {
        doStuff();
        end();
        geraTriagem();

    }
}
