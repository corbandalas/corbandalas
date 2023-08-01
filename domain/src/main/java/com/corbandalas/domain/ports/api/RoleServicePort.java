package com.corbandalas.domain.ports.api;

import com.corbandalas.domain.model.RoleDTO;

import java.util.Optional;

public interface RoleServicePort extends CrudServicePort<RoleDTO> {
    Optional<RoleDTO> getRoleByName(String name);
}
