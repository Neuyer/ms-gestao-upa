package com.fiap.upa.infrastructure.mapper;

import com.fiap.upa.core.entity.Client;
import com.fiap.upa.infrastructure.repository.model.ClientModel;

public abstract class ClientMapper {
    private ClientMapper() {
    }

    public static Client toEntity(ClientModel clientModel) {
        return new Client(
                clientModel.getId(),
                clientModel.getName(),
                clientModel.getBirthDate(),
                clientModel.getDocument(),
                clientModel.getAddresses()
        );
    }

    public static ClientModel toModel(Client client) {
        return ClientModel.builder()
                .id(client.getId())
                .name(client.getName())
                .birthDate(client.getBirthDate())
                .document(client.getDocument().getValue())
                .addresses(client.getAddresses())
                .build();

    }
}
