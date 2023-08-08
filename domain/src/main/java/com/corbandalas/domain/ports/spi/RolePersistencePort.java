package com.corbandalas.domain.ports.spi;

import com.corbandalas.domain.model.RoleDTO;

import java.util.Optional;

public interface RolePersistencePort extends CrudPersistencePort<RoleDTO> {

    Optional<RoleDTO> retrieveRoleByName(String name);

}
