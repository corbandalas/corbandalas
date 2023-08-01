package com.corbandalas.domain.ports.api;

import com.corbandalas.domain.model.CustomerDTO;
import com.corbandalas.domain.model.RoleDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerServicePort extends CrudServicePort<CustomerDTO> {
    Optional<CustomerDTO> retrieveByEmail(String name);

}
