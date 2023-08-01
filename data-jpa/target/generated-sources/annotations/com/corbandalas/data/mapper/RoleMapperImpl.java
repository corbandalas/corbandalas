package com.corbandalas.data.mapper;

import com.corbandalas.data.model.Role;
import com.corbandalas.domain.model.RoleDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-26T21:16:48+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleDTO toDto(Role entity) {
        if ( entity == null ) {
            return null;
        }

        RoleDTO.RoleDTOBuilder<?, ?> roleDTO = RoleDTO.builder();

        roleDTO.uuid( entity.getUuid() );
        roleDTO.name( entity.getName() );

        return roleDTO.build();
    }

    @Override
    public Role toEntity(RoleDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Role.RoleBuilder<?, ?> role = Role.builder();

        role.uuid( dto.getUuid() );
        role.name( dto.getName() );

        return role.build();
    }

    @Override
    public List<RoleDTO> toDto(List<Role> entities) {
        if ( entities == null ) {
            return null;
        }

        List<RoleDTO> list = new ArrayList<RoleDTO>( entities.size() );
        for ( Role role : entities ) {
            list.add( toDto( role ) );
        }

        return list;
    }

    @Override
    public List<Role> toEntity(List<RoleDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Role> list = new ArrayList<Role>( dtos.size() );
        for ( RoleDTO roleDTO : dtos ) {
            list.add( toEntity( roleDTO ) );
        }

        return list;
    }
}
