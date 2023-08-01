package com.corbandalas.service;

import com.corbandalas.domain.model.RoleDTO;
import com.corbandalas.domain.ports.api.RoleServicePort;
import com.corbandalas.domain.ports.spi.RolePersistencePort;

import java.util.Optional;


public class RoleServiceImpl extends CrudServiceImpl<RoleDTO> implements RoleServicePort {

    private final RolePersistencePort rolePersistencePort;

    public RoleServiceImpl(RolePersistencePort rolePersistencePort) {
        super(rolePersistencePort);
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
//    @Transactional(readOnly = true)
    public Optional<RoleDTO> getRoleByName(String name) {
        return rolePersistencePort.getRoleByName(name);
    }
}
