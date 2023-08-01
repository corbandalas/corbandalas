package com.corbandalas.data.mapper;

import com.corbandalas.data.model.Customer;
import com.corbandalas.data.model.Role;
import com.corbandalas.domain.model.CustomerDTO;
import com.corbandalas.domain.model.RoleDTO;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-26T21:16:48+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    private final RoleMapper roleMapper;

    @Autowired
    public CustomerMapperImpl(RoleMapper roleMapper) {

        this.roleMapper = roleMapper;
    }

    @Override
    public CustomerDTO toDto(Customer entity) {
        if ( entity == null ) {
            return null;
        }

        CustomerDTO.CustomerDTOBuilder<?, ?> customerDTO = CustomerDTO.builder();

        customerDTO.uuid( entity.getUuid() );
        customerDTO.email( entity.getEmail() );
        customerDTO.name( entity.getName() );
        customerDTO.active( entity.isActive() );
        customerDTO.hashedPassword( entity.getHashedPassword() );
        customerDTO.roles( roleSetToRoleDTOSet( entity.getRoles() ) );

        return customerDTO.build();
    }

    @Override
    public Customer toEntity(CustomerDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Customer.CustomerBuilder<?, ?> customer = Customer.builder();

        customer.uuid( dto.getUuid() );
        customer.name( dto.getName() );
        customer.email( dto.getEmail() );
        customer.active( dto.isActive() );
        customer.hashedPassword( dto.getHashedPassword() );
        customer.roles( roleDTOSetToRoleSet( dto.getRoles() ) );

        return customer.build();
    }

    @Override
    public List<CustomerDTO> toDto(List<Customer> entities) {
        if ( entities == null ) {
            return null;
        }

        List<CustomerDTO> list = new ArrayList<CustomerDTO>( entities.size() );
        for ( Customer customer : entities ) {
            list.add( toDto( customer ) );
        }

        return list;
    }

    @Override
    public List<Customer> toEntity(List<CustomerDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Customer> list = new ArrayList<Customer>( dtos.size() );
        for ( CustomerDTO customerDTO : dtos ) {
            list.add( toEntity( customerDTO ) );
        }

        return list;
    }

    protected Set<RoleDTO> roleSetToRoleDTOSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleDTO> set1 = new LinkedHashSet<RoleDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( roleMapper.toDto( role ) );
        }

        return set1;
    }

    protected Set<Role> roleDTOSetToRoleSet(Set<RoleDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Role> set1 = new LinkedHashSet<Role>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleDTO roleDTO : set ) {
            set1.add( roleMapper.toEntity( roleDTO ) );
        }

        return set1;
    }
}
