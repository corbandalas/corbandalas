package com.corbandalas.domain.ports.spi;

import com.corbandalas.domain.model.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerPersistencePort extends CrudPersistencePort<CustomerDTO> {
    Optional<CustomerDTO> retrieveByEmail(String email);
}
