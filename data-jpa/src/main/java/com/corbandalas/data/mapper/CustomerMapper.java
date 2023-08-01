package com.corbandalas.data.mapper;

import com.corbandalas.data.model.Customer;
import com.corbandalas.domain.model.CustomerDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = RoleMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer> {

}
