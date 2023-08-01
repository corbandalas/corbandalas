package com.corbandalas.data.mapper;

import com.corbandalas.data.model.Role;
import com.corbandalas.domain.model.RoleDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {
}