package com.fiap.upa.infrastructure.mapper;

import com.fiap.upa.core.entity.Address;
import com.fiap.upa.core.entity.Client;
import com.fiap.upa.core.entity.UPA;
import com.fiap.upa.infrastructure.repository.model.AddressModel;
import com.fiap.upa.infrastructure.repository.model.ClientModel;
import com.fiap.upa.infrastructure.repository.model.UPAModel;

public abstract class AddressMapper {
    private AddressMapper() {
    }

    public static Address toEntity(AddressModel addressModel) {
        return Address.loadAddress(
                addressModel.getId(),
                addressModel.getStreet(),
                addressModel.getCity(),
                addressModel.getState(),
                addressModel.getZipCode(),
                addressModel.getNumber()
        );
    }

    public static AddressModel toModel(Address address) {
        return AddressModel.builder()
                .id(address.getId())
                .city(address.getCity())
                .number(address.getNumber())
                .state(address.getState())
                .street(address.getStreet())
                .zipCode(address.getZipCode())
                .build();

    }
}
