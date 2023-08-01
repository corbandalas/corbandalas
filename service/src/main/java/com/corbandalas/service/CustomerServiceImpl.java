package com.corbandalas.service;

import com.corbandalas.domain.model.CustomerDTO;
import com.corbandalas.domain.ports.api.CustomerServicePort;
import com.corbandalas.domain.ports.api.PasswordEncoder;
import com.corbandalas.domain.ports.spi.CustomerPersistencePort;

import java.util.Optional;
import java.util.UUID;

public class CustomerServiceImpl extends CrudServiceImpl<CustomerDTO> implements CustomerServicePort {

    private final CustomerPersistencePort customerPersistencePort;
    private final PasswordEncoder passwordEncoder;

    public CustomerServiceImpl(CustomerPersistencePort customerPersistencePort, PasswordEncoder passwordEncoder) {
        super(customerPersistencePort);
        this.customerPersistencePort = customerPersistencePort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<CustomerDTO> retrieveByEmail(String email) {
        return customerPersistencePort.retrieveByEmail(email);
    }

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {

        customerDTO.setUuid(UUID.randomUUID());

        final String encodedPassword = passwordEncoder.encode(customerDTO.getHashedPassword());
        customerDTO.setHashedPassword(encodedPassword);

        return customerPersistencePort.create(customerDTO);
    }



}
